from functools import reduce

def slices(digits, length):
    if length > len(digits):
        raise ValueError('Invalid slice length')

    integers = [int(digit) for digit in digits]
    groups = [integers[idx:idx+length] for idx in range(0, len(digits)) if idx != len(digits) - 1 ]

    return groups


def largest_product(*args):
    groups = slices(*args)
    
    if not groups:
        return 1

    return max(reduce(lambda x,y: x*y, group) for group in groups)




if __name__ == '__main__':
    print(slices("", 0))
