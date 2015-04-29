def prime_factors(number):
    factor_list = []
    current_factor = 2
    current_number = number
    while current_number > 1:
        while divisible(current_number, current_factor):
            factor_list.append(current_factor)
            current_number /= current_factor
        current_factor += 1 
    return factor_list

def divisible(number, divisor):
    return number % divisor == 0
