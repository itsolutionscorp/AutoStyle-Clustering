def difference(n):
    return square_of_sum(n)-sum_of_squares(n)


# aux fun for tail recursion
def aux_sum_of_squares(acc,n):
    if n==0:
        return acc
    else:
        return aux_sum_of_squares(acc+n**2,n-1)

# use tail
def sum_of_squares(n):
        return aux_sum_of_squares(0,n)

def square_of_sum(n):
        return sum(n)**2


def sum(n):
        return n*(n+1)/2
