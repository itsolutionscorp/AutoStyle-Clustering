def difference(num):
    return square_of_sum(num) - sum_of_squares(num)

def sum_of_squares(num):
    ans = 0
    for i in range(1, num+1):
        ans += i**2

    return ans

def square_of_sum(num):
    ans = 0
    for i in range(1, num+1):
        ans += i
    ans = ans**2

    return ans
