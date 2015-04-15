def square_of_sum(nums):
    return reduce(lambda x, y: x + y, range(1, nums + 1)) ** 2


def sum_of_squares(nums):
    return reduce(lambda x, y: x + y,
                  map(lambda x: x ** 2, range(1, nums + 1)))


def difference(nums):
    return square_of_sum(nums) - sum_of_squares(nums)
