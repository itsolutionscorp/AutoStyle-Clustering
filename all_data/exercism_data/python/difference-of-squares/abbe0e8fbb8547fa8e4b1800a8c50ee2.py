# square_of_sum(n) >= sum_of_squares(n)
def difference(n):
    return square_of_sum(n) - sum_of_squares(n)

# http://jeremykun.com/2011/06/24/sums-of-the-first-n-numbers-squares/
def square_of_sum(n):
    Sn = n*(n+1)/2
    return Sn*Sn

def sum_of_squares(n):
    return n*(n+1)*(2*n+1)/6

for n in range(-3,3,1):
    print(sum_of_squares(n), square_of_sum(n))
