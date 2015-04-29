def square_of_sum(number):
    return sum(range(1, number + 1)) ** 2

def sum_of_squares(number):
    return sum(i ** 2 for i in range(1, number + 1))

def difference(number):
    return square_of_sum(number) - sum_of_squares(number)

# class DifferenceOfSquares(object):
#     def __init__(self):
#         pass

#     def square_of_sum(self, number):
#         return sum(range(1, number + 1)) ** 2

#     def sum_of_squares(self, number):
#         return sum(i ** 2 for i in range(1, number + 1))

#     def difference(self, number):
#         return self.square_of_sum(number) - self.sum_of_squares(number)
