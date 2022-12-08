#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Jun 21 14:11:56 2022

@author: rohansingh

This is a list of the numeric columns for ids:
    num_cols = ["TENANT","CID","BUCKETID","ACCOUNTID","SUBSCRIPTIONID","CONTRACTID","MATERIALID","STATUS_CODE"] 
    
This is a list of the columns containing phone numbers:                        
    phone_num_cols = ["SOURCE","DESTINATION"]
    
This is a list of columns containing hexadecimal ids:
    hex_cols = ["ID',"BATCH_ID"]
                 
This is a list of columns containing dates: (Not to be masked)
    dates = ["DATETIME","CREATEDAT"]
    
This is a list of columns to be redacted from the dataframe (Not for now):
    redact_cols = ["RUNID","ORIGIN_HOST","ORIGINID","DISPOSITION","PRERATED","MISC","MESSAGE"]
"""

import pandas as pd
import matplotlib.pyplot as mpl
import numpy as np

df = pd.read_csv("/Users/rohansingh/Internship/Data Masking/Dataset/Masked Datasets/Masked_2.csv")

redact_cols = ["RUNID","ORIGIN_HOST","ORIGINID","DISPOSITION","PRERATED","MISC","MESSAGE"]

df = df.drop(redact_cols, axis = 1)

x_axis = np.array(df["TENANT"])
y_axis = np.array(df["USAGE"])
z_axis = np.array(df["PRICE"])

mpl.plot(x_axis,y_axis,z_axis,'o')

mpl.show()

#print(df.head())

#%%
from mpl_toolkits import mplot3d
import numpy as np
import matplotlib.pyplot as plt
 
fig = plt.figure()
 
# syntax for 3-D projection
ax = plt.axes(projection ='3d')
 
# defining all 3 axes
"""z = np.linspace(0, 1, 100)
x = z * np.sin(25 * z)
y = z * np.cos(25 * z)"""
 
x_list = []
y_list = []
z_list = []

for i in range (0,100,1):
    x_list.append(i)
    y_list.append(i)
    
x = np.outer(np.linspace(-2, 2, 10), np.ones(10))
y = x.copy().T
z = np.array(x+y)

# plotting
ax.plot_surface(x, y, z,  cmap ='viridis', edgecolor ='green')
ax.set_title('very cool plot!')
plt.show()

#%%