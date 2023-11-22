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
insert_sql = 'insert into spot_visitors(year,title,visitor) values(%s,%s,%s)'

df = pd.read_excel('data/spot_visitors_sm.xlsx', engine='openpyxl',index_col=0)

for i in df.index:
  year = i
  for col in df.columns:
    title = col
    visitor = df.loc[i][col]
    curs.execute(insert_sql,(year,title,visitor))
conn.commit()
print('insert Database')
