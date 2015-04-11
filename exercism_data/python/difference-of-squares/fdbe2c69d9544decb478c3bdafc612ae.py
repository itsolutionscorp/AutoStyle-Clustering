def square_of_sum(number):
    square_of_sum = 0
    for i in (range(1, number + 1)):
        square_of_sum += i
    return square_of_sum ** 2

def sum_of_squares(number):
    sum_of_squares = 0
    for i in (range(1, number + 1)):
        sum_of_squares += i ** 2
    return sum_of_squares

def difference(number):
    s_sum = 0
    sum_s = 0
    s_sum = square_of_sum(number)
    sum_s = sum_of_squares(number)
    return s_sum - sum_s

# class DifferenceOfSquares(object):
#     def __init__(self):
#         self.sum = 0
#         self.square = 0

#     def square_of_sum(self, number):
#         for i in (range(1, number + 1)):
#             self.sum += i
#         return self.sum ** 2

#     def sum_of_squares(self, number):
#         for i in (range(1, number + 1)):
#             self.square += i ** 2
#         return self.square

#     def difference(self, number):
#         self.sum = self.square_of_sum(number)
#         self.square = self.sum_of_squares(number)
#         return self.square - self.sum
