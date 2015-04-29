def square_of_sum(self):
    count = 0
    for a in range(self+1):
        count += a
    return count * count
def sum_of_squares(self):
    count = 0
    for a in range(self+1):
        count += a*a
    return count
def difference(self):
    return abs(sum_of_squares(self) - square_of_sum(self))
