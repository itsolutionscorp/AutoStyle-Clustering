#Records if it's a leapyear
#on every year that is evenly divisible by 4
#except every year that is evenly divisible by 100
#unless the year is also evenly divisible by 400

def is_leap_year(year):
       
    by4=year%4
    
    if (by4==0):
        by100=year%100   
        if(by100==0):
            by400=year%400        
            if(by400==0):
                return True     
            else:
                return False         
        else:
            return True
    else:
        return False
        
        
        
