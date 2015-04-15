#!/usr/bin/python
def numeral(number):
    numerals = dict(zip([1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1], "M CM D CD C XC L XL X IX V IV I".split()))
    split_number = filter(lambda l: l[1] != '0', list(enumerate(list(str(number))[::-1])))
    number_column = [(int(x[1]), int(x[1]) * (10 ** x[0]), "", x[0]) for x in split_number]
    numeral_mapping = (lambda x: numeral_mapping(
        (x[0], 
         x[1] - max([k for k in numerals.keys() if k <= x[1]]),
         x[2] + numerals.get(max([k for k in numerals.keys() if k <= x[1]])),
         x[3])) 
        if x[1] > 0 
        else x)
    result = "".join([numeral_mapping(col)[2] for col in number_column][::-1])
    return result
