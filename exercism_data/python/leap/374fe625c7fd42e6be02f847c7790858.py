def is_evenly_divisible(num, divisor):
    return num % divisor == 0

def all_evenly_divisible(num, divisors):
    return all([is_evenly_divisible(num, d) for d in divisors])

def is_leap_year(year):
    
    if all_evenly_divisible(year, [4,100, 400]): return True
    if all_evenly_divisible(year, [4,100]): return False
    if is_evenly_divisible(year, 4): return True

    return False
