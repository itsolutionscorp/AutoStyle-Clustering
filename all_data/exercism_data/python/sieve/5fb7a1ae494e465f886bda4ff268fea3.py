__author__ = 'jeffmarkey'

def sieve(total):

    if (total < 2):
        return []

    all_numbers = list(xrange(2,total))

    for line in range(2,total):
        for num in all_numbers:
            if ((num%line == 0) and (num > line)):
                all_numbers.remove(num)

    return all_numbers
