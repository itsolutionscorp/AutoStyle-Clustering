__author__ = 'nebur1989'


def parse_octal(octal):
    acum = 0
    for position, element in enumerate(reversed(octal)):
        element = int(element)
        if element < 0 or element > 7:
            raise ValueError
        acum += element * (8**position)
    return acum
