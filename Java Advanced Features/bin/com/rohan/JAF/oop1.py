# -*- coding: utf-8 -*-
"""
Created on Tue Jun 22 20:17:38 2021

@author: Rohan
"""

#%%
class Employee:
    def __init__(self,first,last,pay):
        self.first = first                               #self means instance
        self.last = last
        self.pay = pay
        self.email = first + "." + last + "@company.com" 
        
    def fullname(self):
        return self.first + " " + self.last
    
    def makeArray(self):
        self.arry = []
        for i in range(0,10,1):
            temp_str = input("Enter a number: ")
            temp = int(temp_str)
            self.arry.append(temp)
            
    def summary(self):
        import statistics
        print("Mean is: ",statistics.mean(self.arry))
        print("Standard Deviation is: ",statistics.stdev(self.arry))
    
#%%

emp_1 = Employee("Rohan","Singh",100000)
emp_2 = Employee("Test","User",80000)

print(emp_1.fullname())          #getting the email of emp_1
print(emp_2.fullname())          #getting the last name of emp_2

#%%