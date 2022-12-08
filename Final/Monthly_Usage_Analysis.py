#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Jul 19 09:45:08 2022

@author: rohansingh
"""

#imports
import pandas as pd
from prophet import Prophet

input_filepath = "/Users/rohansingh/Desktop/API_TF_INBOUND.csv"
output_filepath = "/Users/rohansingh/Desktop/tempu.csv"


df = pd.read_csv(input_filepath)

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

#Plotting the graph
plot1 = p.plot(forecast_prediction)
plot2 = p.plot_components(forecast_prediction)
print("API_TF_INBOUND")

#Saving the forecast prediction to a CSV
forecast_prediction.to_csv(output_filepath)