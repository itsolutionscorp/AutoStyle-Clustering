def sieve(number_range):
    number_list = range(2, number_range)
    for prime in number_list:
        # Loops through the list starting at the next multiple of Prime
        for not_prime in range(prime*2, number_range, prime):
            try:
                number_list.remove(not_prime)
            except ValueError:
                # ValueError raised is to be expected.
                pass

    return number_list
