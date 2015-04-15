#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what == '' or all_whitespace(what):
        return "Fine. Be that way!"
    elif (what.upper() == what) and not(all_sym(what)):
        return "Whoa, chill out!"
    elif '?' == what.replace(" ","")[-1]:
        return "Sure."
    else:
        return "Whatever."

def all_whitespace(string):
    whitespace = {' ', '\t', '\n'}
    for char in string:
        if not (char in whitespace):
            return False
    return True

def all_sym(string):
    syms = list(" 123456789?,<>!@#$%^&*()-_=+")
    for char in string:
        if not(char in syms):
            return False
    return True
