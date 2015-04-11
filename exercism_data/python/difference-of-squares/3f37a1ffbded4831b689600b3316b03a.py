
def square_of_sum(n):
    # can also be sum(i for i in range(1, n+1)])**2
    return int(((n *(n+1))/2)**2)

def sum_of_squares(n):
    return int(sum([i**2 for i in range (1, n+1)]))

def difference(n):
    return square_of_sum(n)- sum_of_squares(n)
