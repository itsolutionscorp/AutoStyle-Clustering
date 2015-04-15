def on_square(num):
    return 2 ** (num - 1)


def total_after(num):
    return reduce(lambda x,y: x + on_square(y), xrange(1,num+1))
