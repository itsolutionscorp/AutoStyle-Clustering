def difference(number_of_int):
    return square_of_sum(number_of_int) - sum_of_squares(number_of_int)

def square_of_sum(number_of_int):
    sum = 0;
    for i in range(1, number_of_int+1):
        sum = sum + i;

    return sum**2;

def sum_of_squares(number_of_int):
    sum = 0;
    for i in range(1, number_of_int+1):
        sum = sum + i**2;

    return sum;
