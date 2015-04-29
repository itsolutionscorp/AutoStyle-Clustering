__author__ = 'jeffmarkey'

import math


def clean(text_value):
    new_value = text_value.lower().replace(' ','').replace('!','').replace("'",'').replace(",","").replace(".","")
    return new_value


def decode(text_value):
    text_value = clean(text_value)
    return decode_helper(text_value)


def decode_helper(text_value):
    square_size = size_of_square(text_value)
    remainder = len(text_value) - (square_size-1)*(square_size)
    if remainder < 0:
        remainder = remainder + square_size
        square_size = square_size - 1
    arr = {}

    for elm in xrange(0,size_of_square(text_value)):
        arr[elm] = []

    total = remainder * square_size
    unfinished = True
    counter = 0
    for line in text_value:
        if counter < total and unfinished:
            elm = counter % square_size
            arr[elm].append(line)
        elif counter == total and unfinished:
            arr[0].append(line)
            counter = 0
            unfinished = False
        else:
            elm = counter % (square_size-1)
            arr[elm].append(line)
        counter = counter + 1

    return_value = ''
    for elm in xrange(0,size_of_square(text_value)):
         return_value = return_value+ ''.join(arr[elm])

    return return_value


def encode(text_value):
    text_value = clean(text_value)
    return encode_helper(text_value, True)


def encode_helper(text_value, space_inclusion):
    square_size = size_of_square(text_value)
    square_list = []
    if len(text_value) == 0:
        return ''
    else:
        for i in xrange(0, len(text_value), square_size):
            square_list.append(text_value[i:i+square_size])

        new_value = []
        for character in range(0,square_size):
            for element in square_list:
                try:
                    new_value.append(element[character])
                except:
                    pass

        final_value = ''
        counter = 0
        for character in new_value:
            counter = counter + 1
            final_value = final_value + character
            if counter % 5 == 0 and counter != len(new_value) and space_inclusion == True:
                final_value= final_value + ' '

        return final_value


def size_of_square(text_value):
    return int(math.ceil(math.sqrt(len(text_value))))
