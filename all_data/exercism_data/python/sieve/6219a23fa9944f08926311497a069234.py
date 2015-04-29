def sieve(num):
    nums_in_range = list(range(2, num+1))
    for n in nums_in_range:
        possible_prime = n
        while possible_prime < num:
            possible_prime += n
            if possible_prime in nums_in_range:
                nums_in_range.remove(possible_prime)
    return nums_in_range


def prime_sieve_two(num):
    primality_list = [True] * num
    primality_list[0] = primality_list[1] = False

    for (i, isprime) in enumerate(primality_list):
        if isprime:
            yield i
            for n in range(i*i, num, i):
                primality_list[n] = False
