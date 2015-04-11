import math

def sieve(max_val):

    # Create list of values
    prime_list = [True] * max_val
    result_list = []

    # Apply Trian division algoritm
    for i in range(2, int(math.sqrt(max_val))):
        if prime_list[i] == True:
            for j in range(i, max_val, i):
                prime_list[j] = False

    # Generate result list
    for i in range(2, max_val):
        if prime_list[i] == True:
            result_list.append(i)

return result_list
