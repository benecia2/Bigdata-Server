# step1. 라이브러리 불러오기
import requests
from sqlalchemy import create_engine
import pymysql
from bs4 import BeautifulSoup as bs
import schedule
import time
import datetime
import pandas as pd
from urllib.parse import urlparse, parse_qs

base_url = 'https://www.visitbusan.net'


# 관광정보 크롤링
def do_cr():

    start = time.time()

    # step2. DB생성
    conn = pymysql.connect(host='localhost',
                        user='root',
                        password='1234',
                        db='btrip',
                        charset='utf8')

    sql = '''CREATE TABLE IF NOT EXISTS tour_list (
            id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
            title varchar(255),
            link_url varchar(255),
            img_url varchar(255),
            views varchar(255),
            comments varchar(255),
            likes varchar(255),
            category varchar(255),
            category_num int
            )
            '''

    with conn:
        with conn.cursor() as cur:
            cur.execute(sql)
            conn.commit()

    # step3. 변수 선언

    # URL 정보
    base_url = 'https://www.visitbusan.net'
    url_part_1 = '/index.do?menuCd='
    category_url = [ 'DOM_000000202012000000',
                    'DOM_000000202002000000',
                    'DOM_000000202003000000',
                    'DOM_000000202008000000',]
    
    url_final = [
                'https://www.visitbusan.net/index.do?uc_seqs=&ucMtmUcSeq=&ucMtmItemUcSeq=&file_name=&gugun_nm=&cate2_nm=&ucc1_seq=22&cate1_nm=&ucdpp_seqs=&uct_seqs=&ucum_seqs=&ucl_seq=8&ucl_use_yn=Y&exclude_uc_seq=&place=&title=&subtitl=&hash_tag=&middle_size_rm2=&menuCd=DOM_000000302003000000&list_type=TYPE_SMALL_CARD&order_type=NEW&listCntPerPage2=16&ucum_seq=&ub_seq=&distance=0.0&cate2_month=&favoriteThis=N&myFavoriteUserId=&sel_visit_place=N&user_id=&search_keyword=&num_room=&ulg_seq=&ucc1_use_yn=&ucc2_seq=&ucg_seq=&ucogl_seq=&main_img_ucmf_seq=&main_title=&charger_positn=&charger_nm=&charger_tel=&tripadvisor_id=&lat=&lng=&bundle_cntnts_yn=&use_yn=Y&sort_num=&page_no=',
                'https://www.visitbusan.net/index.do?uc_seqs=&ucMtmUcSeq=&ucMtmItemUcSeq=&file_name=&gugun_nm=&cate2_nm=&ucc1_seq=&cate1_nm=&ucdpp_seqs=&uct_seqs=30&ucum_seqs=&ucl_seq=8&ucl_use_yn=Y&exclude_uc_seq=&place=&title=&subtitl=&hash_tag=&middle_size_rm2=&menuCd=DOM_000000303011000000&list_type=TYPE_SMALL_CARD&order_type=VIEW&listCntPerPage2=16&ucum_seq=&ub_seq=7&distance=0.0&cate2_month=&favoriteThis=N&myFavoriteUserId=&sel_visit_place=N&user_id=&search_keyword=&num_room=&ulg_seq=&ucc1_use_yn=&ucc2_seq=&ucg_seq=&ucogl_seq=&main_img_ucmf_seq=&main_title=&charger_positn=&charger_nm=&charger_tel=&tripadvisor_id=&lat=&lng=&bundle_cntnts_yn=&use_yn=Y&sort_num=&page_no=',
                'https://www.visitbusan.net/index.do?uc_seqs=&ucMtmUcSeq=&ucMtmItemUcSeq=&file_name=&gugun_nm=&cate2_nm=&ucc1_seq=&cate1_nm=&ucdpp_seqs=&uct_seqs=34&ucum_seqs=&ucl_seq=8&ucl_use_yn=Y&exclude_uc_seq=&place=&title=&subtitl=&hash_tag=&middle_size_rm2=&menuCd=DOM_000000303011000000&list_type=TYPE_SMALL_CARD&order_type=VIEW&listCntPerPage2=16&ucum_seq=&ub_seq=7&distance=0.0&cate2_month=&favoriteThis=N&myFavoriteUserId=&sel_visit_place=N&user_id=&search_keyword=&num_room=&ulg_seq=&ucc1_use_yn=&ucc2_seq=&ucg_seq=&ucogl_seq=&main_img_ucmf_seq=&main_title=&charger_positn=&charger_nm=&charger_tel=&tripadvisor_id=&lat=&lng=&bundle_cntnts_yn=&use_yn=Y&sort_num=&page_no=',
                'https://www.visitbusan.net/index.do?uc_seqs=&ucMtmUcSeq=&ucMtmItemUcSeq=&file_name=&gugun_nm=&cate2_nm=&ucc1_seq=&cate1_nm=&ucdpp_seqs=&uct_seqs=31&ucum_seqs=&ucl_seq=8&ucl_use_yn=Y&exclude_uc_seq=&place=&title=&subtitl=&hash_tag=&middle_size_rm2=&menuCd=DOM_000000303011000000&list_type=TYPE_SMALL_CARD&order_type=VIEW&listCntPerPage2=16&ucum_seq=&ub_seq=7&distance=0.0&cate2_month=&favoriteThis=N&myFavoriteUserId=&sel_visit_place=N&user_id=&search_keyword=&num_room=&ulg_seq=&ucc1_use_yn=&ucc2_seq=&ucg_seq=&ucogl_seq=&main_img_ucmf_seq=&main_title=&charger_positn=&charger_nm=&charger_tel=&tripadvisor_id=&lat=&lng=&bundle_cntnts_yn=&use_yn=Y&sort_num=&page_no=',
                'https://www.visitbusan.net/index.do?uc_seqs=&ucMtmUcSeq=&ucMtmItemUcSeq=&file_name=&gugun_nm=&cate2_nm=&ucc1_seq=&cate1_nm=&ucdpp_seqs=&uct_seqs=32&ucum_seqs=&ucl_seq=8&ucl_use_yn=Y&exclude_uc_seq=&place=&title=&subtitl=&hash_tag=&middle_size_rm2=&menuCd=DOM_000000303011000000&list_type=TYPE_SMALL_CARD&order_type=VIEW&listCntPerPage2=16&ucum_seq=&ub_seq=7&distance=0.0&cate2_month=&favoriteThis=N&myFavoriteUserId=&sel_visit_place=N&user_id=&search_keyword=&num_room=&ulg_seq=&ucc1_use_yn=&ucc2_seq=&ucg_seq=&ucogl_seq=&main_img_ucmf_seq=&main_title=&charger_positn=&charger_nm=&charger_tel=&tripadvisor_id=&lat=&lng=&bundle_cntnts_yn=&use_yn=Y&sort_num=&page_no=',
                ]

    # 데이터 담을 변수
    tour_id = []            # 데이터 아이디
    tour_name = []          # 이름
    tour_link_url = []      # 링크 주소
    tour_img_url = []       # 이미지 주소
    tour_views = []         # 조회 수
    tour_comments = []      # 댓글 수
    tour_likes = []         # 좋아요 수
    tour_category = []      # 카테고리
    tour_category_num = []  # 카테고리번호
    id = 1

    seq = 1                 # 페이지 구분 변수
    category_list = [ 'Food Tour', 'Nature', 'Shopping', 'History', 'Culture' ]

    # step4. 여행테마 대분류
    for url_1 in url_final:
        
        # html 정보 받아와서 파싱
        response_2 = requests.get(url_1+'1')
        soup = bs(response_2.text , 'html.parser')

        page = int(soup.select_one('#containernew > div > div > div > div > div.search_array > div.written > span > i').string)
        if page%16 > 0:
            page = 1 + page//16
        else:
            page = page//16


        for page_num in range(page):       

            # range를 이용하면 0부터 인덱스가 시작되므로 page_num에 1을 더해준 url을 이용
            url_2 = f'{url_1}{page_num+1}'

            # html 정보 받아와서 파싱
            response_3 = requests.get(url_2)
            soup = bs(response_3.text , 'html.parser')

            category = category_list[seq-1]

            print('[ ' + category + ' ] : ' + str(page_num+1) + ' / ' + str(page) + ' 페이지 >> 크롤링 작업 [ 시작 ]')

            # css selector로 페이지 내의 원하는 정보 가져오기
            html_title = soup.select('div.info p')
            html_link_url = soup.select('div.box.actionImg3 > a')
            html_img_url = soup.select('div.box.actionImg3 > a > img')
            html_views = soup.select('div.info > span > img:nth-child(1)')
            html_comments = soup.select('div.info > span > img:nth-child(2)')
            html_likes = soup.select('div.info > span > img:nth-child(3)')

            

            # 데이터 갈무리
            for i in html_title:
                tour_id.append(id)
                id = id + 1
                tour_name.append(i.get_text())

            for i in html_link_url:
                tour_link_url.append(base_url+i['href'])    

            for i in html_img_url:
                tour_img_url.append(base_url+i['src'])

            for i in html_views:
                tour_views.append(i.next_sibling.strip())

            for i in html_comments:
                tour_comments.append(i.next_sibling.strip())

            for i in html_likes:
                tour_likes.append(i.next_sibling.strip())
                tour_category.append(category)
                tour_category_num.append(seq)
            
            print('[ ' + category + ' ] 크롤링 작업 [ 완료 ] \n')

        seq += 1
    # step6. zip 모듈을 이용해서 list를 묶어주기        
    tour_list = list(zip(tour_id,
                        tour_name,
                        tour_link_url,
                        tour_img_url,
                        tour_views,
                        tour_comments,
                        tour_likes,
                        tour_category,
                        tour_category_num))

    # step7. 데이터프레임의 첫행에 들어갈 컬럼명
    col = ['id','title', 'link_url', 'img_url','views','comments','likes', 'category', 'category_num']

    # step8. pandas 데이터 프레임 형태로 가공
    df = pd.DataFrame(tour_list, columns=col)



    # step9. DB, 엑셀에 저장
    # DB연결 (sqlalchemy)
    db_con_str = 'mysql+pymysql://root:1234@localhost/btrip'
    db_connection = create_engine(db_con_str)
    conn = db_connection.connect()

    df.to_excel('투어리스트.xlsx')
    df.to_sql(name='tour_list', con=conn, if_exists='replace',index=False)  
    print('<<< 관광 데이터 크롤링 작업 완료 >>>')
    print(datetime.datetime.now())
    end = time.time()
    print('소요시간 : ' + f"{end - start : .5f} sec")
    print('')



# step10. 반복수행(1시간 주기)
schedule.every(1).hour.at(":40").do(do_cr)

do_cr()


while True:
    schedule.run_pending()
    time.sleep(1)
