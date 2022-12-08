FROM python:3.8

ADD Main.py .

ADD requirement.txt .

RUN pip install pandas

RUN pip install dash

RUN pip install dash_extensions

RUN pip install dash_bootstrap_components

#RUN pip install prophet

RUN pip install numpy

RUN pip install scikit-learn

RUN pip install mysql

RUN pip install plotly

RUN pip install wordcloud

RUN pip install -r requirement.txt

#RUN pip install fbprophet

CMD ["python","./Main.py"]
