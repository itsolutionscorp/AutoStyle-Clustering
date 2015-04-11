def slices(digits, series_length):
    digits_length = len(digits)
    results = []
    if series_length > digits_length:
        raise ValueError('Series length exceeded the length of input string of digits')        
    elif series_length == 0:
        raise ValueError('Series length must be greater than 0')
    else:
        digit_list = []
        #Convert each digit to integer
        for d in digits:
            digit_list.append(int(d))
        #List all possible series
        for i in xrange(digits_length - series_length + 1):
            series = digit_list[i:i + series_length]
            results.insert(i, series)
    return results
