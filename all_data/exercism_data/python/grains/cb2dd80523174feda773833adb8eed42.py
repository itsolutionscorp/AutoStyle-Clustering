#grains.py
#Surely a bread winner


def on_square(square):
    return 2**(square-1)


def total_after(number):
    return sum(on_square(num) for num in range(1,number+1))
