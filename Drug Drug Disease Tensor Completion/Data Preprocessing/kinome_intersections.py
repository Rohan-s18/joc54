"""
Author: Rohan Singh
Python Module to find the intersectio between drugs across kinome and dd disease datasets
"""

#  Imports
import pandas as pd
import numpy as np
from pubchempy import *
import random


#  Getting the attributes for kinome
def get_kinome(filepath, attr):
    df = pd.read_csv(filepath)
    temp = df[attr].to_numpy()
    return temp


#  Getting the attributes for dd disease
def get_ddd(filepath, attr):
    df = pd.read_csv(filepath)
    temp = df[attr].to_numpy()
    return temp


#  helper function to get the intersection between the kinome names and the ddi names for drugs
def get_intersection_drugs(db1, db2):
    db1_set  = set(db1)
    db2_set = set(db2)
    intersections = db1_set.intersection(db2_set)
    print(len(intersections))
    return np.array(intersections)


#  Main Function
def main():

    filepath_kinome = "/Users/rohansingh/github_repos/Data-Analysis-and-Machine-Learning/Tensor Decomposition/Datasets/IC50.csv"
    filepath_ddd = "/Users/rohansingh/github_repos/Data-Analysis-and-Machine-Learning/Tensor Decomposition/Datasets/intersection_pairs_dd_disease.csv"

    kinome = get_kinome(filepath_kinome,"CELL_NAME")
    ddd = get_ddd(filepath_ddd,"cell_line")

    intersection = get_intersection_drugs(kinome,ddd)

    print("The Intersection is:")
    print(intersection)

    print("\n\n")

    print(kinome)
    print("\n\n")
    print(ddd)



if __name__ == "__main__":
    main()
