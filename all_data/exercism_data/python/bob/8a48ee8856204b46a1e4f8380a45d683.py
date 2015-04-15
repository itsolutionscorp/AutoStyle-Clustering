#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    #Testing for an empty string, or any lenthg of white space.
    #And return "Fine. Be that way!" if true
    if what.isspace() or what == '':
        return 'Fine. Be that way!'
    #Testing if the string has been written all in uppcase, ignoring the final character
    #and return 'Whoa, chillout!' if true.
    if what.isupper():
        return 'Whoa, chill out!'
    #Testing final character of string if it is a question mark (?)
    #and return 'Sure.' if true.
    if what[-1] == '?':
        return 'Sure.'
    #Finally if not caught by any test before, return 'Whatever.'
    else:
        return 'Whatever.'
