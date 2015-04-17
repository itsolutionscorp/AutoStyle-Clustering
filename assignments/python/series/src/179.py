def slices(digits, series_length):
    digits_length = len(digits)
    results = []
    if series_length > digits_length:
        raise ValueError('Series length exceeded the length of input string of digits')        
    elif series_length == 0:
        raise ValueError('Series length must be greater than 0')
    else:       
        digit_list = [int(d) for d in digits]
        for i in xrange(digits_length - series_length + 1):
            series = digit_list[i:i + series_length]
            results.insert(i, series)
    return results
