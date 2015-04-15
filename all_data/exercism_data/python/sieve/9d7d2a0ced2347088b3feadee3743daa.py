def sieve(n):
    # make a list of 1s refering to 2 to n
    n_list = [1] * (n - 1)
    prime_list = []
    # iterate through each number in the list
    for a in range(2, n):
        # iterate through the number's multiples
        for b in range(2, n):
            # check to make sure its in the range
            if (a * b - 2) < len(n_list):
                # mark it off
                if n_list[a * b - 2] == 1:
                    n_list[a * b - 2] = 0
        # if it hasn't been market off yet, its good to go
        if n_list[a - 2] == 1:
            prime_list.append(a)
    return prime_list
