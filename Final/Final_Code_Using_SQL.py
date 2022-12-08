#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Jul 19 12:53:21 2022

@author: rohansingh
"""

import pandas as pd
from prophet import Prophet
import cx_Oracle
import numpy as np
from sklearn.cluster import DBSCAN as dbs
import sqlalchemy
from sqlalchemy.exc import SQLAlchemyError


"""
input_filepath = "/Users/rohansingh/Desktop/API_TF_INBOUND.csv"
output_filepath = "/Users/rohansingh/Desktop/tempu.csv"


df = pd.read_csv(input_filepath)
"""

rows = []

# Connecting the oracle db for the usage forecasting
try:
    connection = ""
    con = cx_Oracle.connect(connection)
    
    cursor = con.cursor()
    
    command = ""
    
    cursor.execute(command)
    
    rows = cursor.fetchall()
    
 
except cx_Oracle.DatabaseError as e:
    print("There is a problem with Oracle", e)


# Connecting to the oracle db for the outlier analysis
try:
    engine_command = ""
    engine = sqlalchemy.create_engine(engine_command, arraysize = 44312)
    orders_sql = ""; 
    df_1 = pd.read_sql(orders_sql, engine)
    df_copy = pd.read_sql(orders_sql, engine)
    
except SQLAlchemyError as e:
    print(e)
    
    

#Usage analysis starts from here

billing_from_date = []
usage_qty= []
    
for i in range(0,len(rows),1):
    billing_from_date.append(rows[i][13])
    usage_qty.append(rows[i][18])

df = pd.DataFrame(list(zip(billing_from_date,usage_qty)),columns = ["ds","y"])  

#df = pd.read_csv("/Users/rohansingh/Desktop/API_TF_INBOUND.csv")

df["ds"] = pd.to_datetime(df["ds"])

#Adding holiday months
holiday_season = pd.DataFrame({
  'holiday': 'holiday season',
  'ds': pd.to_datetime(['2017-05-31', '2017-06-30','2017-11-30','2017-12-31', 
                        '2018-05-31', '2018-06-30','2018-11-30','2018-12-31',
                        '2019-05-31', '2019-06-30','2019-11-30','2019-12-31',
                        '2020-05-31', '2020-06-30','2020-11-30','2020-12-31',
                        '2021-05-31', '2021-06-30','2021-11-30','2021-12-31',
                        '2022-05-31', '2022-06-30']),
  'lower_window': -1,
  'upper_window': 1,
})

#Creating the model
p = Prophet(interval_width=0.8, daily_seasonality=True,holidays = holiday_season)
model = p.fit(df)

#Creating the future dataframe
future = p.make_future_dataframe(periods= 12, freq='M')

#Doing the forecast prediction
forecast_prediction = p.predict(future)
forecast_prediction = forecast_prediction[["ds","yhat"]]
forecast_prediction.columns = ["ds","yhat_API_TF_INBOUND"]



#df_1 = pd.read_csv("/Users/rohansingh/Internship/Data Analysis/Datasets/masked_event_cdr_regional.csv")
#df_copy = pd.read_csv("/Users/rohansingh/Internship/Data Analysis/Datasets/masked_event_cdr_regional.csv")

#Outlier analysis code starts here
#Dataframes df_1 and df_copy were extracted from db above

source_country_list = []
dest_country_list = []
source_country = df_1["SOURCE_COUNTRY"]
dest_country = df_1["DEST_COUNTRY"]

unfriendly_country_list = ["KP","IR","CU","SY","AF","BY"]
concern_country_list = ["RS","IQ","RU"]

for i in range (0, len(source_country), 1):
    if ((source_country[i] in source_country_list)==False):
        source_country_list.append(source_country[i])
    if((dest_country[i] in dest_country_list)==False):
        dest_country_list.append(dest_country[i])

def source_country_getter(s_country):
    for i in range (0, len(source_country_list), 1):
        if((s_country == source_country_list[i]) & ((s_country in unfriendly_country_list)==True)):
            return 1000+i
        if((s_country == source_country_list[i]) & ((s_country in concern_country_list)==True)):
            return 100+i
        if(s_country == source_country_list[i]):
            return i
    return 0
        
def dest_country_getter(d_country):
    for i in range (0, len(dest_country_list), 1):
        if((d_country == dest_country_list[i]) & ((d_country in unfriendly_country_list)==True)):
            return 1000+i
        if((d_country == dest_country_list[i]) & ((d_country in concern_country_list)==True)):
            return 100+i
        if(d_country == dest_country_list[i]):
            return i
    return 0

src_temp_list = []
dest_temp_list = []
source_country = df_1["SOURCE_COUNTRY"]
dest_country = df_1["DEST_COUNTRY"]

for i in range (0, len(source_country),1):
    src_temp_list.append(source_country_getter(source_country[i]))
    dest_temp_list.append(dest_country_getter(dest_country[i]))
df_1["DEST_COUNTRY"] = src_temp_list
df_1["SOURCE_COUNTRY"] = dest_temp_list

imp_data = df_1[["SOURCE_COUNTRY","ORIGINAL_USAGE","DEST_COUNTRY"]]
imp_data_array = imp_data.to_numpy()

df_1["SOURCE_COUNTRY"] = df_copy["SOURCE_COUNTRY"]
df_1["DEST_COUNTRY"] = df_copy["DEST_COUNTRY"]

db = dbs(eps=30, min_samples=10).fit(imp_data_array)
core_samples_mask = np.zeros_like(db.labels_, dtype=bool)
core_samples_mask[db.core_sample_indices_] = True
labels = db.labels_

regional_df = pd.DataFrame({"SOURCE_COUNTRY":df_copy["SOURCE_COUNTRY"],"ORIGINAL_USAGE":imp_data["ORIGINAL_USAGE"],"DEST_COUNTRY":df_copy["DEST_COUNTRY"],"LABEL":labels.tolist()})


#Dataframe to be used
forecast_prediction                 #For forecasting
regional_df                         #For outlier analysis









