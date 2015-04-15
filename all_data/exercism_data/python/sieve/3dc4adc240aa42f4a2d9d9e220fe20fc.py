def _filter_list(cand_list):
    # If there's only one item left, it's got to be prime
    if len(cand_list) <= 1:
        return cand_list
    # First item in the list is always prime; 
    # it would've been eliminated earlier, otherwise
    prime = cand_list.pop(0)
    # Weed out multiples of the prime number and recursively act on result
    filtered = _filter_list([x for x in cand_list if x % prime != 0])
    # Slap the prime number back on the front of the list
    filtered.insert(0,prime)
    return filtered


def sieve(max_num):
    prime_list = range(2, max_num+1)
    return _filter_list(prime_list)
