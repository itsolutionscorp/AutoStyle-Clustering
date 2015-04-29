__author__ = 'jeffmarkey'

def prime_factors(number):
    return_list = []
    reduced_number = number
    current = 2
    while (current <= reduced_number):
        if(reduced_number % current == 0):
            reduced_number = reduced_number / current
            return_list.append(current)
        else:
            current = current+1
    return return_list

def prime_factors_unsure(number):
    list_to_analyze = all_prime(number)
    print number, list_to_analyze
    return_list = []
    if list_to_analyze == []:
        return list_to_analyze
    else:
        for element in list_to_analyze:
            if(element % number == 0):
                return_list.append(element)
        return return_list

def all_prime(total):
    if (total < 2):
        return []

    all_numbers = list(xrange(2,total))

    for line in range(2,total):
        for num in all_numbers:
            if ((num%line == 0) and (num > line)):
                all_numbers.remove(num)

    return all_numbers
