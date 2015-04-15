__author__ = 'Momo'


def on_square(number):
    return 1 << number - 1

def total_after(number):
    return sum(1 << i for i in range(number))
