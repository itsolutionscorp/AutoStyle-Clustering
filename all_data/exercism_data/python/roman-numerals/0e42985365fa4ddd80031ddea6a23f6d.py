from collections import OrderedDict

letters = OrderedDict([('M', 1000), ('D', 500), ('C', 100), ('L', 50), ('X', 10), ('V', 5), ('I', 1)])


def numeral(number):
    roman = []
    
    for letter, value in letters.items():
        while number >= value:
            number -= value
            roman.append(letter)

    # replace IIII by IV etc.
    for letter in letters:
        if roman.count(letter) == 4:
            for _ in range(3):
                roman.remove(letter)
            roman.insert(roman.index(letter) + 1, larger_letter(letter))
            
    # replace VIV by IX etc.
    value_of_previous_letter = None
    for letter, value in letters.items():
        if value_of_previous_letter is not None and roman.count(letter) * value == value_of_previous_letter:
            index = -1
            while letter in roman:
                index = roman.index(letter)
                roman.remove(letter)
            roman.insert(index, larger_letter(letter))
        value_of_previous_letter = value

    return ''.join(roman)


def larger_letter(current):
    index = letters.keys().index(current)
    return letters.keys()[index - 1]
