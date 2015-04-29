def square_of_sum(n):
    sum_total = 0
    all_numbers = range(1, n + 1)
    
    for number in all_numbers:
        sum_total += number
    
    return sum_total**2


def sum_of_squares(n):
    total = 0
    all_numbers = range(1, n + 1)

    for number in all_numbers:
        total += number**2
        
    return total


def difference(n):
    return square_of_sum(n) - sum_of_squares(n)
