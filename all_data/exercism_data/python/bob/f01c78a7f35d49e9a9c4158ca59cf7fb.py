#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    a = what.strip()
    if len(a) == 0:
        return 'Fine. Be that way!'
    elif a[-1] == '?' and a.upper() != a:
        return 'Sure.'
    elif a[-1] == '?' and a.upper() == a and a.lower() == a:
        return 'Sure.'
    elif len(a.split()) == 0:
        return 'Fine. Be that way!'
    elif a.upper() == a and a.lower() != a:
        return 'Whoa, chill out!'
    else:
        return 'Whatever.'
