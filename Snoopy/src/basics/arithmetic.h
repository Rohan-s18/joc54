//  Author: Rohan Singh
//  12/28/2022
//  This header file contains source code for the simple arithmetic stuff required for Snoopt

#include <string.h>
#include <stdio.h>
#include <stdlib.h>

//  Add Function
double snoopy_add(char* values){

    char *parsed_values;
    parsed_values = strtok(values,")");
    

    char *val_1 = strtok(parsed_values,",");
    char *val_2 = strtok(NULL,",");

    double d1 = atof(val_1);
    double d2 = atof(val_2);

    return d1+d2;

}


//  Subtract Function
double snoopy_subtract(char* values){

    char *parsed_values;
    parsed_values = strtok(values,")");
    

    char *val_1 = strtok(parsed_values,",");
    char *val_2 = strtok(NULL,",");

    double d1 = atof(val_1);
    double d2 = atof(val_2);

    return d1-d2;

}


//  Multiply function
double snoopy_multiply(char* values){

    char *parsed_values;
    parsed_values = strtok(values,")");
    

    char *val_1 = strtok(parsed_values,",");
    char *val_2 = strtok(NULL,",");

    double d1 = atof(val_1);
    double d2 = atof(val_2);

    return d1*d2;

}


//  Divide function
double snoopy_divide(char* values){

    char *parsed_values;
    parsed_values = strtok(values,")");
    

    char *val_1 = strtok(parsed_values,",");
    char *val_2 = strtok(NULL,",");

    double d1 = atof(val_1);
    double d2 = atof(val_2);

    return d1/d2;

}


