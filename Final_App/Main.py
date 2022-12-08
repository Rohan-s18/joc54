from dash import Dash, dcc, html, Input, Output # pip install dash           

import pandas as pd
from prophet import Prophet
import numpy as np
from sklearn.cluster import DBSCAN as dbs

import mysql.connector as sql


from dash_extensions import Lottie       # pip install dash-extensions
import dash_bootstrap_components as dbc  # pip install dash-bootstrap-components
import plotly.express as px              # pip install plotly
import pandas as pd                      # pip install pandas
from datetime import date
import calendar
from wordcloud import WordCloud 
#import os         # pip install wordcloud
import plotly.graph_objs as go

# Lottie by Emil - https://github.com/thedirtyfew/dash-extensions
url_coonections = "https://assets9.lottiefiles.com/private_files/lf30_5ttqPi.json"
url_companies = "https://assets9.lottiefiles.com/packages/lf20_EzPrWM.json"
url_msg_in = "https://assets9.lottiefiles.com/packages/lf20_8wREpI.json"
url_msg_out = "https://assets2.lottiefiles.com/packages/lf20_Cc8Bpg.json"
url_reactions = "https://assets2.lottiefiles.com/packages/lf20_nKwET0.json"
options = dict(loop=True, autoplay=True, rendererSettings=dict(preserveAspectRatio='xMidYMid slice'))


import pandas as pd
from prophet import Prophet
#import cx_Oracle
import numpy as np
from sklearn.cluster import DBSCAN as dbs
#import sqlalchemy
#from sqlalchemy.exc import SQLAlchemyError



input_filepath = "/Users/rohansingh/Desktop/API_TF_INBOUND.csv"
output_filepath = "/Users/rohansingh/Desktop/tempu.csv"

db_connection = sql.connect(host='localhost', database='ROHANDB1', user='root', password='Cb4dYb4z*')

#df = pd.read_csv(input_filepath)
df = pd.read_sql('SELECT * FROM API_TF_INBOUND', con=db_connection)


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

"""

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


df_1 = pd.read_sql('SELECT * FROM masked_event_cdr', con=db_connection)


#df_1 = pd.read_csv("/Users/rohansingh/Internship/Data Analysis/Datasets/masked_event_cdr_regional.csv")
df_copy = pd.read_sql('SELECT * FROM masked_event_cdr', con=db_connection)

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




#df1 = pd.read_csv(os.path.join(os.getcwd(),'Monthly_Dummy_Usage_2.csv'),delimiter=',')
df1 = forecast_prediction
df4 = pd.read_sql('SELECT * FROM masked_usage_run', con=db_connection)
df2 = regional_df



app = Dash(__name__, external_stylesheets=[dbc.themes.LUX])


    
fig = px.line(df1, x='ds', y="yhat_API_TF_INBOUND")
    

#USAGE PREDICTION PLOT
fig1= px.line(df1, x="ds", y=df1.columns,
              hover_data={"ds": "|%B %d, %Y"},
              )
fig1.update_xaxes(
        dtick="M1",
        tickformat="%b\n%Y",
        rangeslider_visible=True,
     )

#OUTLIER PLOT
trace1 = go.Scatter3d(
    x = df2['SOURCE_COUNTRY'],
    y = df2['DEST_COUNTRY'],
    z = df2['ORIGINAL_USAGE'],
    mode='markers',
    marker=dict(
        size=12,
        color=df2['LABEL'],                
        colorscale='Viridis',  
        opacity=0.8
    )
)



data = [trace1]
layout = go.Layout(
    scene = dict(
                    xaxis = dict(
                        title='Source'),
                    yaxis = dict(
                        title='Dest'),
                    zaxis = dict(
                        title='Usage'),),
    margin=dict(
        r=20, b=10, l=10, t=10
    )
)
fig3 = go.Figure(data=data, layout=layout)


#FRONT END LAYOUT

app.layout = dbc.Container([
    dbc.Row([
        dbc.Col([
            dbc.Card([
                dbc.CardImg(src='avayaimage.png') # 150px by 45px
            ],className='mb-2'),
            dbc.Card([
                dbc.CardBody([
                    dbc.CardLink("Visit The Avaya Official Page", target="_blank",
                                 href="https://avaya.com"
                    )
                ],style={'textAlign':'center'})
            ]),
        ], width=2),
        dbc.Col([
            dbc.Card([
                dbc.CardBody([
                    html.H1('Avaya Analytics Dashboard'),
                    html.H3('SIP / CPASS OFFERS')
                ],style={'textAlign':'center'})
            ], color="info", style={'height':'18vh'}),
        ], width=8),
    ],className='mb-2 mt-2'),
    dbc.Row([
        dbc.Col([
            dbc.Card([
                dbc.CardHeader(Lottie(options=options, width="67%", height="67%", url=url_coonections)),
                dbc.CardBody([
                    html.H6('Countries'),
                    html.H2(id='content-connections', children=len(df4['COUNTRY_CODE'].unique()))
                ], style={'textAlign':'center'})
            ]),
        ], width=2),
        dbc.Col([
            dbc.Card([
                dbc.CardHeader(Lottie(options=options, width="32%", height="32%", url=url_companies)),
                dbc.CardBody([
                    html.H6('Materials'),
                    html.H2(id='content-companies', children=len(df4['MATERIAL_NUMBER'].unique()))
                ], style={'textAlign':'center'})
            ]),
        ], width=2),
        dbc.Col([
            dbc.Card([
                dbc.CardHeader(Lottie(options=options, width="25%", height="25%", url=url_msg_in)),
                dbc.CardBody([
                    html.H6('Subscribers'),
                    html.H2(id='content-msg-in', children= len(df4['SUBSCRIPTION_NUMBER'].unique()))
                ], style={'textAlign':'center'})
            ]),
        ], width=2),
        dbc.Col([
            dbc.Card([
                dbc.CardHeader(Lottie(options=options, width="53%", height="53%", url=url_msg_out)),
                dbc.CardBody([
                    html.H6('Usage Qty'),
                    html.H2(id='content-msg-out', children=(df4['USAGE_QTY'].sum()))
                ], style={'textAlign': 'center'})
            ]),
        ], width=2),
        dbc.Col([
            dbc.Card([
                dbc.CardHeader(Lottie(options=options, width="25%", height="25%", url=url_reactions)),
                dbc.CardBody([
                    html.H6('Services'),
                    html.H2(id='content-reactions', children=len(df4['SERVICE_TYPE'].unique()))
                ], style={'textAlign': 'center'})
            ]),
        ], width=2),
    ],className='mb-2'),
    dbc.Row([
        dbc.Col([
            dbc.Card([
                dbc.CardBody([html.H6('USAGE PREDICTION'),
                     dcc.Graph(id='line-chart',figure=fig)
                             
                    
                ],style={'textAlign': 'center'})
            ]),
        ], width=6),
        dbc.Col([
            dbc.Card([
                dbc.CardBody([html.H6('OUTLIER ANALYSIS'),
                    dcc.Graph(id='bar-chart', figure=fig3),
                ],style={'textAlign': 'center'})
            ]),
        ], width=6),
    ],className='mb-2'),
    dbc.Row([
        dbc.Col([
            dbc.Card([
                dbc.CardBody([html.H6('USAGE PREDICTION COMPARISON'),
                    dcc.Graph(
                         id='world-chart',
                         figure=fig1
                            ), 
                ],style={'textAlign': 'center'})
            ]),
            ],width=12 ),
    ],className='mb-2'),
], fluid=True)

# USAGE PREDICTION COMPARISON PLOT

"""@app.callback(
    Output('line-chart','figure'),
    Input("ticker", "value"))"""


    
app.run_server(debug=True)







