#!/usr/bin/python3


def square_of_sum(number):
    return (sum(range(1, number+1)))**2


def sum_of_squares(number):
    return sum(num**2 for num in range(1, number+1))


def difference(number):
    return square_of_sum(number) - sum_of_squares(number)


if __name__ == "__main__":
    pass
