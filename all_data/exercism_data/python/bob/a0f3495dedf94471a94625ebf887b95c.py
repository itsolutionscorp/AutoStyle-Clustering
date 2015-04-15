#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    #first remove all trailing space characters from the beginning and end of the string
    what = what.strip( ' \t\n\r' )

    # check if anything was said
    if len( what ) == 0:
        return 'Fine. Be that way!'

    # if all characters are in uppercase it is a yell
    if what.isupper():
        return 'Whoa, chill out!'

    # if the last character is a question mark, then it is a question
    if what[len(what)-1] == '?':
        return 'Sure.'

    # return whatever otherwise
    return 'Whatever.'
