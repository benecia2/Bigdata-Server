import pymysql;
import pandas as pd;

#db연결
dbURL = '127.0.0.1'
dbPort = 3306
dbUser = 'root'
dbPass = '1234'

conn = pymysql.connect(host=dbURL,port=dbPort,user=dbUser, passwd=dbPass,
                       db='btrip', charset='utf8', use_unicode=True)
curs = conn.cursor(pymysql.cursors.DictCursor)
insert_sql = 'insert into variable_importance(variable, importance) values(%s,%s)'

df = pd.read_excel('data/variable_importance.xlsx', engine='openpyxl')

for i in df.index:
  variable = df.loc[i]['Variable(y)']
  importance = df.loc[i]['Importance(x)']
  curs.execute(insert_sql,(variable,importance))
conn.commit()
print('insert DataBase')
