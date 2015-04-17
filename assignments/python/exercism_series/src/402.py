def slices(number, length):
    ''' takes a string of digits and returns all the possible
        consecutive number series of length 'n' in that string.
        number: str or int
        length: int > length of 'number'
        result: 2d array (list of lists) of number series
    '''
    assert (type(number) == str) or (type(number) == int), "Invalid input. Must be 'str' or 'int'."
    if (type(number) == int): number = str(number)
    if (length > len(number)) or (length < 1): raise ValueError("'length' must be less than than the number string length")
    number_list = [int(digit) for digit in number]
    if (length == 1):
        return [[number] for number in number_list]
    result = []
    for i in range(len(number_list) - length + 1):
        result.append(number_list[i:i+length])
    return result
