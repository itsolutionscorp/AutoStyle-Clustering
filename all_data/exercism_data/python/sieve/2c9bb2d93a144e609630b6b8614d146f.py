from math import sqrt

def is_prime(test_num):
    for i in range(2,test_num):
        if test_num % i == 0:
            return False
    return True

def sieve(number_range):
    number_list = list(range(2,number_range+1))
    prime_list = []
    for num in number_list:
        if is_prime(num) == True:
            prime_list.append(num)
    return prime_list
