from operator import mul

def slices(string, step):
    if not string or not step:
        return
    if step > len(string):
        raise ValueError('Invalid Step Size.')
    return [map(int, string[i:i+step]) for i in range(len(string)-step+1)]

def largest_product(string, step):
    slice_list = slices(string, step)
    return 1 if not slice_list else max([reduce(mul, l) for l in slice_list])
