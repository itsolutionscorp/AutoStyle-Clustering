def slices(number, length):
    ''' takes a string of digits and returns all the possible
        consecutive number series of length 'n' in that string.
        number: str or int
        length: int > length of 'number'
        result: 2d array (list of lists) of number series
    '''
    # error handling and typecasting
    if type(number) == str or type(number) == int:
        raise TypeError("Invalid input. Must be 'str' or 'int'.")
    if type(number) == int:
        number = str(number)
    if length > len(number) or length < 1:
        raise ValueError("'length' must be less than than the number string length")

    # convert number string into list
    number_list = [int(digit) for digit in number]

    # initialize result
    result = []

    # iterate through number_list in size 'length' chunks
    for i in range(len(number_list) - length + 1):
        result.append(number_list[i:i+length])

    # return result
    return result
