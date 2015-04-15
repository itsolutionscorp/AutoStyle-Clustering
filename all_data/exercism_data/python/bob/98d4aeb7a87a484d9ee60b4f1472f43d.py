#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    response = "Whatever."
    what = what.strip()

    if len(what) == 0:
        response = 'Fine. Be that way!'
    elif isShouting(what):
        response = 'Whoa, chill out!'
    elif what.find('?') == len(what) - 1:
        response = "Sure."

    return response

def isShouting(what):
    hasAlpha = False
    allUppers = True

    for c in what:
        if c.isalpha():
            hasAlpha = True
            if c == c.lower():
                allUppers = False

    return hasAlpha == True and allUppers == True
