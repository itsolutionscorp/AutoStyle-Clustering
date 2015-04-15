#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    if what.isupper():  #Checks for shouting (which an exclamation mark does not imply)
        return 'Whoa, chill out!'
    elif what == '':    #Empty string catch before checking last char to avoid IndexError
        return 'Fine. Be that way!'
    elif what[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
