def square_of_sum(num):
    if type(num)!=type(314) or num>100 or num<-1:
        return "You must input an positive integer that is less than 101"
    total=0
    while num>0:
        total=total+num
        num=num-1
    return total*total


def sum_of_squares(num):
    if type(num)!=type(159):
        return "You must input an integer"
    total=0
    while num>0:
        total=total+(num*num)
        num=num-1
    return total

def difference(num):
    if type(num)!=type(265):
        return "You must input an integer"
    return abs(square_of_sum(num)-sum_of_squares(num))
