# Implementation of Bob, a lackadaisical teenager


def contains_alpha(str):
    """check if str contains any alphabet characters"""
    for x in str:
        if x.isalpha():
            return True
    return False


def hey(str):
    """Say str to bob.  Bob responds (lackadaisically)!"""
    str = str.strip()
    if not str:
        return "Fine. Be that way!"
    elif str.upper() == str and contains_alpha(str):
        return "Whoa, chill out!"
    elif str[-1] == '?':
        return "Sure."
    else:
        return "Whatever."
