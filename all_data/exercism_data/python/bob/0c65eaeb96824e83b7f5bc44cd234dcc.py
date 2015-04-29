#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

# remove extra whatspace from imput string
    what = what.strip(' \t\n\r')
# get string length
    what_length = len(what)
# if string is empty
    if what_length == 0:
        return 'Fine. Be that way!'
# if string is all caps
    if what.isupper():
        return 'Whoa, chill out!'
# if string ends with a ?
    if '?' == what[-1]:
        return 'Sure.'
# all other cases
    else:
        return 'Whatever.'
