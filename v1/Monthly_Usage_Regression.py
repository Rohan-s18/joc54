#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Jul  7 16:17:22 2022

@author: rohansingh
"""

import pandas as pd
import matplotlib.pyplot as mtp
import numpy as np

#Giving the filepath for the csv
df = pd.read_csv("/Users/rohansingh/Internship/Data Analysis/Datasets/masked.csv")

date_time = df["DATETIME"]
sub_id = df["SUBSCRIPTIONID"]

#Getting the month from the date-time column
month_list = []
for i in range (0,44299,1):
    date = date_time[i]
    if((date[1] != '/') & (date[1] != '-')):
        month_list.append(date[0:2])
    else:
        month_list.append(date[0])

#Creating a 2-D array for the monthly usage of all of the subscriptions
monthly_usage_sub = [[0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0]]
original_usage = df["ORIGINAL_USAGE"]

#Adding the monhtly usage for each of the subscribers
for i in range (0,44299,1):
    month = int(month_list[i])
    monthly_usage_sub[(int(sub_id[i]))-1000000][month-1] += original_usage[i]


#Getting the polynomial regression model using numpy
monthly_usage_sub
months = [1,2,3,4,5,6,7,8,9,10,11,12]
months = np.array(months)

#Note: the line of fit and the points are of type 'numpy n-d array'
for i in range (0,len(monthly_usage_sub),1):
    monthly_usage = np.array(monthly_usage_sub[i])
    mymodel = np.poly1d(np.polyfit(months, monthly_usage, 6))
    myline = np.linspace(1, 12, 200)
    mtp.scatter(months, monthly_usage)
    mtp.plot(myline, mymodel(myline))
    mtp.show()