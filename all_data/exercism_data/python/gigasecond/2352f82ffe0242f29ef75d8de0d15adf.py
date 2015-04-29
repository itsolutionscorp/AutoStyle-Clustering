import datetime

def if_leap(year):
    
    if year%4==0 and not year%100==0:
        return True
    elif year%400==0:
        return True
    else:
        return False

def count_leap(year1):
    count=0
    for year in range(year1,year1+32,1):
        if if_leap(year)==1:
            count+=1
    return count

def gigasecond(year1):
    d=10**9/(60*60*24)
    y=11574/365.0
    r=11574-((31*365)+count_leap(year1))
    return r

def add_gigasecond(date1):
	year=int(date1.year)
	month=int(date1.month)
	day=int(date1.day)
	days_list=(0,31,28,31,30,31,30,31,31,30,31,30,31)
	days_list1=(0,365,334,306,275,245,214,184,153,122,92,61,31)
	days_list2=[0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365]
	days_left=0
	for i in days_list[month:]:
		days_left+=i
	days_left-=day
	r=gigasecond(year)
	if days_left>r:
		x=abs(365-days_left-r)
		for m in range(len(days_list2)):
			if x<days_list2[m]:
				d=abs(days_list2[m-1]-(x+2))
				break
		year+=31
		y=year
		date2=datetime.date(y,m,d)
		return date2
	else:
		x=abs(days_left-r)
		for m in range(len(days_list2)):
			if x<days_list2[m]:
				d=abs(days_list2[m-1]-x)
				break
		year+=32
		y=year
		date2=datetime.date(y,m,d)
		return date2
