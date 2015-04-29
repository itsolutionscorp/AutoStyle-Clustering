def sum_of_multiples(n, config=[3, 5]):
    if n < 2 or (len(config) == 1 and config[0] == 0):
        return 0
    return reduce(lambda x, y: x + y,
               filter(lambda x: find_multiple(x, config), [i for i in range(1,n)]))
def find_multiple(n, config):
    for i in config:
        if i == 0:
            pass
        elif n % i != 0:
            pass
        else:
            return n
