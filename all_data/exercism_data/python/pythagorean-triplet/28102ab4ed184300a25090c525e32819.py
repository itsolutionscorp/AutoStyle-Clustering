from math import sqrt

def primitive_triplets(num):
    if num % 2 <> 0:
        raise ValueError
    ret = []
    for i in range(2, num / 2 + 1):

        for y in range(1, i):
            if (i - y) % 2 == 0:
                continue
            elif y > 1 and i % y == 0:
                continue
            if (i ** 2) - (y ** 2) == num or 2 * i * y == num:
                ls = [(i ** 2) - (y ** 2), 2 * i * y, (i ** 2) + (y ** 2)]
                ls.sort()
                ret.append(tuple(ls))
    return set(ret)

def triplets_in_range(fm, to):
    ret = []
    tmp = []
    f = int(sqrt(fm)) - 1
    if f < 2:
        f = 2
    t = int(sqrt(to))
    if t < 2:
        t = 2
    for i in range(fm, to + 1):
        for y in range(1, i):
            if sqrt(i**2 + y**2) % 1 == 0:
                ls = [i, y, int(sqrt(i**2 + y**2))]
                ls.sort()
                tmp.append(tuple(ls))

    for l in tmp:
        if l[0] >= fm and l[2] <= to:
            ret.append(l)
    return set(ret)

def is_triplet(nums):
    ls = list(nums)
    ls.sort()
    if ls[0] % 2 == 0:
        ret = primitive_triplets(ls[0])
        if len(ret) > 0:
            return True
    if ls[1] % 2 == 0:
        ret = primitive_triplets(ls[1])
        if len(ret) > 0:
            return True
    return False
