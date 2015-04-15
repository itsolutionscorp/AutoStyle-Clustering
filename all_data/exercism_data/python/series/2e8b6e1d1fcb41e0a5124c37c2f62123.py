__author__ = 'tracyrohlin'

from operator import mul

def slices(series, n):
    series = list(series)
    series = [int(x) for x in series]
    series_list = []
    i = 0
    if len(series) >= n:
        if n != 0:
            for x in range(len(series)):
                if x+n-1 < len(series):
                    series_list.append(series[x:x+n])
            return series_list
        else:
            raise ValueError()
    else:
        raise ValueError()

def largest_product(string, n):

    new_number = (list(string))
    long_number = [int(x) for x in new_number]

    if string:

        if len(long_number) < n:
            raise ValueError()


        else:
            new_list = slices(string, n)
            product = 0
            for item in new_list:
                if reduce(mul, item) > product:
                    product = reduce(mul, item)
            return product
    else:
        return 1

print largest_product("", 0)
