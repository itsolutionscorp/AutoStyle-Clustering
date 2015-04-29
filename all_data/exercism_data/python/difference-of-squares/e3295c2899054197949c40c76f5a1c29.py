'''
# Difference Of Squares

Find the difference between the sum of the squares and the square of the sums of the first N natural numbers.

The sum of the squares of the first ten natural numbers is,

    1**2 + 2**2 + ... + 10**2 = 385

The square of the sum of the first ten natural numbers is,

    (1 + 2 + ... + 10)**2 = 55**2 = 3025

Hence the difference between the sum of the squares of the first ten
natural numbers and the square of the sum is 3025 - 385 = 2640.
'''

def difference(num=1):
    return square_of_sum(num) - sum_of_squares(num)
    
def sum_of_squares(num=1):
    
    x=0
    
    for i in range(num):
        x += (i+1)**2
        
    return x
    
    
def square_of_sum(num=1):
    
    x = 0;
    
    for i in range(num):
        x += i+1
        
    return x**2
