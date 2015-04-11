from collections import namedtuple

Square = namedtuple('Square', ['qty', 'total'])

squares = {1: Square(1,1)}

def next_square(square):
    qty = square.qty * 2
    return Square(qty, square.total + qty)

for i in xrange(1,64):
    squares[i+1] = next_square(squares[i])

def on_square(number):
    return squares[number].qty

def total_after(number):
    return squares[number].total
