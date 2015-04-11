def parse_binary(inputstring):

    if any([char not in '10' for char in inputstring]):
        raise ValueError("Invalid binary value.")

    return sum([2**(len(inputstring)-1-place)
                for place in range(len(inputstring))
                if inputstring[place] =='1'])                                
