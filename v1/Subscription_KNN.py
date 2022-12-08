#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Mon Jun 27 21:25:38 2022

@author: rohansingh
"""

#Imports
import pandas as pd
import matplotlib.pyplot as mtp
import numpy as nm
from sklearn.cluster import DBSCAN as dbs
from sklearn import metrics
from sklearn.model_selection import train_test_split  
from sklearn.preprocessing import StandardScaler   
from sklearn.neighbors import KNeighborsClassifier  
from sklearn.metrics import confusion_matrix  
from matplotlib.colors import ListedColormap 
from sklearn.model_selection import train_test_split 
from sklearn.preprocessing import StandardScaler 
from sklearn.neighbors import KNeighborsClassifier  

#Reading the csv and storing the data in a dataframe
df = pd.read_csv("/Users/rohansingh/Internship/Data Analysis/Datasets/masked_event_cdr.csv")

#Creating the outlier usage threshold (value of 200 after DBSCAN analysis)
THRESHOLD = 200

"""
The following code will convert the Service Type from String to Number
"""
my_list = []
service_type = df["SERVICE_TYPE"]
for i in range (0, len(service_type), 1):
    if ((service_type[i] in my_list)==False):
        my_list.append(service_type[i])
my_list

def service_num(s_type):
    for i in range (0, len(my_list), 1):
        if(s_type == my_list[i]):
            return i
        
service_num_list = []
service_type = df["SERVICE_TYPE"]
for i in range (0, len(service_type),1):
    service_num_list.append(service_num(service_type[i]))
df["SERVICE_TYPE"] = service_num_list

"""
End of Helper code
"""

#Exrtacting the valuable data and converting it into a numpy array
x = df[["SERVICE_TYPE","USAGE"]]
x = x.to_numpy()

#Creating the output of whether the data is outlier or not (based on DBSCAN analysis)
#Logic: the output is '1' if the usage is over the given threshold 
y = []
usage = df["USAGE"]
ct = 0
for i in range (0, len(usage), 1):
    if(usage[i] > THRESHOLD):
        y.append(1)
        ct = ct+1
    else:
        y.append(0)
y = nm.array(y)

#Training the data  based on the set that we provide
x_train, x_test, y_train, y_test= train_test_split(x, y, test_size= 0.25, random_state=0)  
st_x= StandardScaler()    
x_train= st_x.fit_transform(x_train)    
x_test= st_x.transform(x_test) 


classifier= KNeighborsClassifier(n_neighbors=5, metric='minkowski', p=2 )  
classifier.fit(x_train, y_train) 

y_pred= classifier.predict(x_test) 

#plotting the data using a pyplot scatterplot
x_set, y_set = x_train, y_train  
x1, x2 = nm.meshgrid(nm.arange(start = x_set[:, 0].min() - 1, stop = x_set[:, 0].max() + 1, step  =0.01),  
nm.arange(start = x_set[:, 1].min() - 1, stop = x_set[:, 1].max() + 1, step = 0.01))  
mtp.contourf(x1, x2, classifier.predict(nm.array([x1.ravel(), x2.ravel()]).T).reshape(x1.shape),  
alpha = 0.75, cmap = ListedColormap(('red','green' )))  
mtp.xlim(x1.min(), x1.max())  
mtp.ylim(x2.min(), x2.max())  

for i, j in enumerate(nm.unique(y_set)):  
    mtp.scatter(x_set[y_set == j, 0], x_set[y_set == j, 1],  
        c = ListedColormap(('red', 'green'))(i), label = j)  
    
mtp.title('K-NN Algorithm (Training set)')  
mtp.xlabel('Service Type')  
mtp.ylabel('Usage')  
mtp.legend()  
mtp.show() 





















