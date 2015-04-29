__author__ = 'tracyrohlin'

def on_square(n):
    return 2 ** (n-1)

def total_after(num):
    if num == 1:
        return on_square(num)
    else:
        return on_square(num) + total_after(num - 1)
