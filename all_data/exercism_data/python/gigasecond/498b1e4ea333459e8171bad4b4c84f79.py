from datetime import date

def is_leap_year(y):
    
    if (y%400==0)or ((y%4==0)and (not y%100==0)):
        return True
    return False
                

def add_gigasecond(x):
    total = 11574
    d=x.day
    m=x.month
    y=x.year
    for n in range(total):     
        if d<=27:
            d+=1
            continue
        elif d==28:
            if m==2:
                if is_leap_year(y):
                    d+=1
                    continue
                else:
                    d=1
                    m+=1
                    continue
            elif m!=2:

                d+=1
                continue
        elif d==29:
            if m==2:
                d=1
                m=3
                continue
            else:
                d+=1
                continue
        elif d==30:
            if m in [9,4,6,11]:
                d=1
                m+=1
                continue
            else:
                d=31
                continue
        else:
            d=1
            m+=1
            if m==13:
                m=1
                y+=1
    return date(y,m,d)              

            

        
        
    
