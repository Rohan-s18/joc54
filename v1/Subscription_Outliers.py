#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Mon Jun 27 21:13:05 2022

@author: rohansingh

Source Code to find the outliers for each Service Type provided
"""

#Imports
import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
from sklearn.cluster import DBSCAN as dbs

#Reading the csv file and storing the data into a dataframe
df = pd.read_csv("/Users/rohansingh/Internship/Data Analysis/Datasets/masked_event_cdr.csv")

#Extracting the relevant data from the dataframe
imp_data = df[["CID","USAGE"]]
imp_array = imp_data.to_numpy()

#Using DBSCAN from the Sklearn library
db = dbs(eps=100, min_samples=10).fit(imp_array)
core_samples_mask = np.zeros_like(db.labels_, dtype=bool)
core_samples_mask[db.core_sample_indices_] = True
labels = db.labels_
n_clusters_ = len(set(labels)) - (1 if -1 in labels else 0)

#Creating the color combinations
unique_labels = set(labels)
colors = ['y', 'b', 'g', 'r']

"""
#Plotting the points in a scatterplot using pyplot
for k, col in zip(unique_labels, colors):
    if k == -1:
        col = 'k'
 
    class_member_mask = (labels == k)
 
    xy = imp_array[class_member_mask & core_samples_mask]
    plt.plot(xy[:, 0], xy[:, 1], 'o', markerfacecolor=col,
             markeredgecolor='k',
             markersize=6)
 
    xy = imp_array[class_member_mask & ~core_samples_mask]
    plt.plot(xy[:, 0], xy[:, 1], 'o', markerfacecolor=col,
             markeredgecolor='k',
             markersize=6)
 
plt.title('number of clusters: %d' % n_clusters_)
plt.show()
"""

# X,Y,Z axes and the cluster information (labels) are all being converted in the form of list, outliers have a data value of 0
x_axis = df["CID"].tolist()
y_axis = df["USAGE"].tolist()
point_labels = labels.tolist()

















 
