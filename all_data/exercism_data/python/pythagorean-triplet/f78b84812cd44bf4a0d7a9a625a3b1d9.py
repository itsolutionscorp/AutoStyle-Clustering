__author__ = 'tracyrohlin'

from prime_factors import prime_factors

def factors(n):
    return set(reduce(list.__add__,
                ([i, n//i] for i in range(1, int(n**0.5) + 1) if n % i == 0)))

def is_triplet(num_tuples):
    num_list = list(num_tuples)
    a, b, c = num_list[0], num_list[1], num_list[2]
    a, b, c = a**2, b**2, c**2
    if a+b == c:
        return True
    elif a+c == b:
        return True
    elif b+c == a:
        return True
    return False

def triplets_in_range(minimum, maximum):
    list_of_triplets = []
    for a in xrange(minimum, maximum+1):
        for b in xrange(minimum+1, maximum+1):
            for c in xrange(minimum+2, maximum+1):
                triplets = [a,b,c]
                if is_triplet(tuple(triplets)):
                    triplets.sort()
                    if triplets not in list_of_triplets:
                        list_of_triplets.append([a, b, c])
    list_of_tuples = [tuple(x) for x in list_of_triplets]
    return set(list_of_tuples)

def primitive_triplets(b):
    if b % 4 == 0:
        list_of_triplets = triplets_in_range(1, 1766)
        sorted_list = list(list_of_triplets)
        sorted_list.sort()
        result = []
        for item in sorted_list:
            temp_list = list(item)
            print temp_list
            if b in temp_list:
                print "I'm in the if statement"
                result.append(item)
        return result


    else:
        raise ValueError


print primitive_triplets(84)
