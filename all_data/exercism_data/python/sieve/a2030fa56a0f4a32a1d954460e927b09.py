import math
def sieve(max):
    number_list = [i for i in range(2, max+1)]
    for i in range(2, int(math.sqrt(max)+1)):
        if i in number_list:
            for j in range(i**2, max+1, i):
                if j in number_list:
                    number_list.remove(j)
    return number_list
