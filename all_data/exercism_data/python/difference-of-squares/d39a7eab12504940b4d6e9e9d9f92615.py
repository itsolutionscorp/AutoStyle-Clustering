def sum_of_squares(nNumber):
    i=1
    sum=0
    while(i<nNumber+1):
        sum+=i**2
        i+=1
    return sum

def square_of_sum(nNumber):
    i=0
    sum=0
    while(i<nNumber+1):
        sum+=i
        i+=1
    return sum**2

def difference(nNumber):
    return square_of_sum(nNumber)-  sum_of_squares(nNumber)


print difference(5)  
