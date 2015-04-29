import sys

def sum_of_squares(natural_number):
    sum = 0
    for i in range(0,natural_number):
        sum += (i + 1) ** 2
    return sum   

def square_of_sum(natural_number):
    sum = 0
    for n in range(0, natural_number):
        sum += n + 1
    return sum **2

def difference(natural_number):
    diff = square_of_sum(natural_number) - sum_of_squares(natural_number)
    return diff
    
if __name__ == "__main__":
    difference = difference(int(sys.argv[1]))
    print difference
