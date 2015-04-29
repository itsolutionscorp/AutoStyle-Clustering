def is_leap_year(yr):
    if(yr%4 ==0):
    #year = float(yr)
        if((yr%100)== 0):
                if((yr%400) == 0):
                    return True
                else:
                    return False
        else:
            return True
          #elif ((yr%4)==0):
           # return True
    else:
        return False
