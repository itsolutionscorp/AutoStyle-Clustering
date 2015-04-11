def difference(n):
    return square_of_sum(n) - sum_of_squares(n)

def square_of_sum(n):
    ans = 0
    for i in range(n+1):
        ans += i
    return ans**2

def sum_of_squares(n):
    ans = 0
    for i in range(n+1):
        ans += i**2
    return ans
