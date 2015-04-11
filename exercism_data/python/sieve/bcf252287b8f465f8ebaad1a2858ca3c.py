def sieve(max_num):
    return find_primes(range(2, max_num), primes=[])


def find_primes(num_list, primes=[]):
    primes.append(num_list[0])
    new_list = [i for i in num_list if i % num_list[0]]
    if new_list:
        return find_primes(new_list, primes)
    else:
        return primes
