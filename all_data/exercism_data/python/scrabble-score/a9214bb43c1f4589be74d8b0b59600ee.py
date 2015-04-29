__author__ = 'Eric'

import string


def populate_values(value_dict, letters, value):
    for letter in letters:
        value_dict[letter] = value


values = {}
populate_values(values, 'AEIOULNRST', 1)
populate_values(values, 'DG', 2)
populate_values(values, 'BCMP', 3)
populate_values(values, 'FHVWY', 4)
populate_values(values, 'K', 5)
populate_values(values, 'JX', 8)
populate_values(values, 'QZ', 10)


def score(word):
    total = 0
    for letter in word:
        if letter in string.ascii_letters:
            total += values[letter.upper()]
    return total
