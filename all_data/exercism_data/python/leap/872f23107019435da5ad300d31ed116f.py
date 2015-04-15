def is_leap_year(year):
    if (not type(year)is int) or (len(str(year))!=4):
        print "PLEASE INSERT A VALID YEAR"
        return False    
    else:
        is_div = year%4
        is_ev_100 = year%100
        is_ev_400 = year%400
        if (is_div == 0 and is_ev_100 ==0 and is_ev_400==0) or (is_div == 0 and is_ev_100 !=0):
            return True
        else:
            return False

            
        
