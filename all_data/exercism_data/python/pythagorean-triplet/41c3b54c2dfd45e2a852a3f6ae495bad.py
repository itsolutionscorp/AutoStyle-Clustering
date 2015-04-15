from fractions import gcd


def primitive_triplets(value):
    return_value = set()
    if value % 4 != 0:
        raise ValueError
    else:
        new_value = value/2
        to_test = get_all_factors(new_value)
        for elm in to_test:
            if (new_value / elm) > elm and coPrime(new_value/elm,elm):
                test = (new_value / elm)**2 - elm**2
                another = (new_value / elm)**2 + elm**2
                if test > value: return_value.add((value,test,another))
                else: return_value.add((test,value,another))
    return return_value


def coPrime (a, b):
    if (gcd(a, b) != 1):
      return False
    else:
      return True


def get_all_factors(value):
    return set(reduce(list.__add__,
                ([i, value//i] for i in range(1, int(value**0.5) + 1) if value % i == 0)))


def triplets_in_range(start, end):
    return_list = set()
    for one in xrange(start,end+1):
        for two in xrange(one,end+1):
            for three in xrange(two, end+1):
                if is_triplet((one,two,three)) and (one < two < three):
                        return_list.add((one,two,three))
    return return_list


def is_triplet(pythagorean_tuple):
    new_tuple = sorted(pythagorean_tuple)
    if new_tuple[0]**2 + new_tuple[1]**2 == new_tuple[2]**2:
        return True
    else:
        return False
