import re

def parse_binary(binary):
    if not re.match(r'^[01]+$', binary):
        raise ValueError
    
    power = 0
    result = 0
    for val in reversed(binary):
        result += int(val) * 2 ** power
        power += 1
    return result
