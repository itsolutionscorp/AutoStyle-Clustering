
def sum_of_multiples(max, factors=[3, 5]):
    result = 0
    for i in range(1, max):
        for j in factors:
            if j != 0 and i % j == 0:
                result += i
                break
    return result
