#The tricky thing here is that a leap year occurs:
#on every year that is evenly divisible by 4
#  except every year that is evenly divisible by 100
#    unless the year is also evenly divisible by 400

def is_leap_year(what):

    if type(what) == int:
        if what >= 0:
            if what % 4 == 0:
                if what % 100 == 0:
                    if what % 400 == 0:
                        #input is int & >=0
                        #dataMOD4=0
                        #dataMOD100=0
                        #dataMOD400=0
                        #so is  leap year
                        return True
                    #input is int & >=0
                    #dataMOD4=0
                    #dataMOD100=0
                    #dataMOD400 != 0
                    #so NOT leap year
                    return False
                #input is int & >=0
                #dataMOD4=0
                #dataMOD100 !=0
                #dataMOD400 !=0
                #so is  leap year
                return True
            #input is int & !>=0 so NOT leap year
            return False
        #input is !int so NOT leap year
        return False
    #no input so NOT leap year
    return False
