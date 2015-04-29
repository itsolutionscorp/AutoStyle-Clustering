def slices(digits, series_length):
    digits_length = len(digits)
    results = []
    if series_length > digits_length:
        raise ValueError('Series length exceeded the length of input string of digits')   
    else:       
        #Convert each digit to integer
        digit_list = [int(d) for d in digits]
        #List all possible series
        for i in xrange(digits_length - series_length + 1):
            series = digit_list[i:i + series_length]
            results.insert(i, series)
    return results
    
def largest_product(digits, series_length):
    try:
        series = slices(digits, series_length)
        largest = 0
        for s in series:
            p = get_product(s)
            if p > largest:
                largest = p
        return largest
    except ValueError, e:
        raise e
    
def get_product(num_list):
    product = 1
    for i in xrange(0, len(num_list)):
        product *= num_list[i]
    return product
