def square_of_sum(limit):
    total = 0;
    for x in range(limit + 1):
        total += x;
    return total**2;

def sum_of_squares(limit):
    total = 0;
    for x in range(limit + 1):
        total += x**2;
    return total;

def difference(limit):
    return abs(sum_of_squares(limit) - square_of_sum(limit));
