__author__ = 'emiller42'


def sieve(max_value):

    # create a list and populate it with values from 2 to the max value
    prime_list = range(2, max_value+1)

    # iterate through the list, for each value found
    # loop through it's multiples and if they exist
    # remove them from the list
    for i in prime_list:
        for j in range(i+i, max_value+1, i):
            if j in prime_list:
                prime_list.remove(j)

    return prime_list
