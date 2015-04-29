import re

def hey(input_str):
    has_caps = False
    has_lc = False
    has_letters = re.search(('[^\W]'), input_str)
    for letter in input_str:
        if letter.isupper():
            has_caps= True
        elif letter.islower():
            has_lc = True
            break
    if has_caps and not has_lc:
        return('Whoa, chill out!')
    elif len(input_str) > 1 and input_str[-1] == '?':
        return('Sure.')
    elif not has_letters:
        return('Fine. Be that way!')
    else:
        return('Whatever.')
