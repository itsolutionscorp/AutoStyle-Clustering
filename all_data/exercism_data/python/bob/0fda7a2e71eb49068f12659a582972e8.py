def hey(str):
    str = ''.join(str.split())
    if len(str) == 0:
        return "Fine. Be that way!"
    elif isalluppercase(str):
        str = ''.join(str.split(","))
        if str.isdigit() and str[-1] != '?':
            return "Whatever."
        if str[0] == '4':
            return "Sure."
        return "Whoa, chill out!"
    elif str[-1] == '?':
        return "Sure."
    else:
        return "Whatever."

def isalluppercase(str):
    out = True
    for s in str:
        if s.islower():
            out = False
    return out
