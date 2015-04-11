import time


def timeit(f):

    def timed(*args, **kw):

        ts = time.time()
        result = f(*args, **kw)
        te = time.time()

        print('%s took: %2.4f sec' % (f.__name__, te-ts))
        return result

    return timed
# Timing function left in because I was playing about with a few things


def square_of_sum(n):
    return sum(x for x in range(n+1)) ** 2


def sum_of_squares(n):
    return sum(x**2 for x in range(n+1))


# @timeit
def difference(n):
    return square_of_sum(n) - sum_of_squares(n)
