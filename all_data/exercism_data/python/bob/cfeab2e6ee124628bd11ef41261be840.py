import string
def hey(x):
    x = x.strip()
    if x == '':
        return 'Fine. Be that way!'
    if (x == x.upper() and x[-1:] == "!") or x[:-1] == x[:-1].upper() and x[0] in string.ascii_uppercase:
        return 'Woah, chill out!'
    if x[-1:] == '?':
        return "Sure."
    else:
        return "Whatever."





