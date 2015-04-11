#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    str_trim = what.strip()
    if str_trim == '':
        return 'Fine. Be that way!'
    elif str_trim.isupper():
        return 'Whoa, chill out!'
    elif str_trim[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
