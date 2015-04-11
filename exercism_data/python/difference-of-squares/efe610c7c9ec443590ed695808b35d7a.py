def square_of_sum(num):
    mylist = list()
    for i in range(1, num+1):
        mylist.append(i)
    return sum(mylist)**2


def sum_of_squares(num):
    mylist = list()
    for i in range(1, num+1):
        mylist.append(i**2)
    return sum(mylist)

def difference(num):
    return square_of_sum(num) - sum_of_squares(num)
