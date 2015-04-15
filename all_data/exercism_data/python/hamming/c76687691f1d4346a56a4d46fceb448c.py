
def distance(str1, str2):
    count = 0
    i = 0
    while i < len(str1):
        if str1[i] != str2[i]:
            count += 1
        i += 1
    return count
        

##def distance(str1,str2):
##    count = 0
##    for x in str1:
##        if x not in str2:
##            count += 1
##    return count
            
        
        
