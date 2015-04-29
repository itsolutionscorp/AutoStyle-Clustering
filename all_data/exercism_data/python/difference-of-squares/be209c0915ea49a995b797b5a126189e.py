def difference(N):
    return square_of_sum(N) - sum_of_squares(N)

def square_of_sum(N):
    count = N
    first_sum = 0
    while count != 0:
        first_sum += count
        count -=1
    return first_sum**2

def sum_of_squares(N):
    count = N
    numbers = []
    while count != 0:
        numbers.append(count)
        count -=1
    numbers_squared = [x**2 for x in numbers]
    return sum(numbers_squared)
