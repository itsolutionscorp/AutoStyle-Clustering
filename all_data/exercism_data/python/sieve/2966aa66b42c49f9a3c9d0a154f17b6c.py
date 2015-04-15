__author__ = 'tracyrohlin'


def sieve(n):
    if n == 2: #adds 2 because 2 is always a prime number
        number_list = [2]
        return number_list

    number_list = range(2, n+1)
    i = 0
    while i < n:
        for item in number_list[i+1:]: #checking the section of the list from the next number up
            if item % number_list[i] == 0:
                number_list.remove(item) #if any number further in the list is a multiple of the current number,
                                        # it removes ("filters") the number
        i += 1
    return number_list
