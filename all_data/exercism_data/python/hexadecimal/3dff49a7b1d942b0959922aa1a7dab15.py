import re

def hexa(value):
    if ishexa(value):
        return int(value, 16)
    else:
        raise ValueError

def ishexa(value):
    l_value = str(value)
    if re.match('^[0-9a-fA-F]+$', value, flags=0):
        return True
    else:
        return False
