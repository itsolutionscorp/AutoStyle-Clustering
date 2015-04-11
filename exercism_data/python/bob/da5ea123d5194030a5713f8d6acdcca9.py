#
# Skeleton file for the Python "Bob" exercise.
#
def stripDigits(what):
    digits = "0123456789"
    strippedWhat =""
    for eachChar in what:
        if eachChar not in digits:
            strippedWhat = strippedWhat+eachChar
    return strippedWhat

def stripPunctuations(what):
    puncts=",'.\""
    strippedWhat =""
    for eachChar in what:
        if eachChar not in puncts:
            strippedWhat = strippedWhat+eachChar
    return strippedWhat

def hey(what):
    trimmedWhat = what.strip()
    x = len(trimmedWhat)-1
    y = len(trimmedWhat)
    lastChar = trimmedWhat[x:y]


    if what.strip() == '':
        return 'Fine. Be that way!'
    if trimmedWhat == trimmedWhat.upper() and stripPunctuations(stripDigits(trimmedWhat[0:len(trimmedWhat)-1])).strip() != '':
        return 'Whoa, chill out!'
    if lastChar == '?':
        return 'Sure.'
    return 'Whatever.'
