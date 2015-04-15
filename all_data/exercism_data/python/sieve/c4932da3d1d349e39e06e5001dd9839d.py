def sieve(number_range):
    number_list = range(2, number_range)
    for prime in number_list:
        for idx, check in enumerate(number_list):
            # If prime mods into check evenly, delete it. Skip the first element.
            if check % prime == 0 and check != prime:
                number_list.pop(idx)
    return number_list
