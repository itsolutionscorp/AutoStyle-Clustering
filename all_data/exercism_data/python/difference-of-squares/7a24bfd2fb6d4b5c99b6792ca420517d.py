def square_of_sum(x):
    return sum(range(1, x+1))**2

def sum_of_squares(x):
    sos = 0
    for i in range(1, x+1):
        sos = sos + i**2
    return sos
	
def difference(x):
    return square_of_sum(x) - sum_of_squares(x)
