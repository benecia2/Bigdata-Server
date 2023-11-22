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
insert_sql = 'insert into silhouette_scores(n,silhouette_scores ) values(%s,%s)'

df = pd.read_excel('data/silhouette_scores_data.xlsx', engine='openpyxl')

for i in df.index:
  n = df.loc[i]['N(x)']
  silhouette_scores = df.loc[i]['Silhouette_Scores(y)']
  curs.execute(insert_sql,(n,silhouette_scores))

conn.commit()
print('insert DataBase')
