

def sum_of_squares(N):
    ans = 0
    for i in range(1, N + 1):
        ans += i**2
    return ans

def square_of_sum(N):
    ans = 0
    for i in range(1, N + 1):
        ans += i
    return ans**2

def difference(N):
    return square_of_sum(N) - sum_of_squares(N)
