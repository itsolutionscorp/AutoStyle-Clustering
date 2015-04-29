def slices(series, test_length):
    # make sure test_length is valid
    if (test_length < 0) or (test_length > len(series)):
        raise ValueError("The length is out of bounds of the series!")
    else:
        return [[int(n) for n in series[i:i + test_length]]
                for i in range(len(series) - test_length + 1)]

def product(s):
    x = 1
    for i in s:
        x *= i
    return x
    
def largest_product(series, test_length):
    return max([product(s) for s in slices(series, test_length)])
    
    
