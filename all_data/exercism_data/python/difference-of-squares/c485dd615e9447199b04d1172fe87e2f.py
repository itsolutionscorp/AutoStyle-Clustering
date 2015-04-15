def square_of_sum(number):
    return (sum(range(number+1))) ** 2

def sum_of_squares(number):
    return (sum([x**2 for x in range(number+1)]))


def difference(inp):
    return  square_of_sum (inp) - sum_of_squares(inp)
