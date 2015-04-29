#
# Skeleton file for the Python "Bob" exercise.
#

import string
def hey(what):

    statement = 'Whatever.'

    #Create string without whitespace
    noWhiteSpace = what.replace(' ', '')

    #Fine. Be that way!
    #null strings or blank string
    if not noWhiteSpace.strip():
        return 'Fine. Be that way!'


    #Shouting
    #assume shout is true

    #has at least one that is a letter
    if any(c.isalpha() for c in noWhiteSpace):
        statement = 'Whoa, chill out!'

        #all alpha characters are capital letters
        for i in noWhiteSpace:
            if i.isalpha() and not i.isupper():
                statement = 'Whatever.'
                exit

    #Question mark
    #must be at the end of string
    #remove whitespace and check if question mark is the last character
    if not statement == 'Whoa, chill out!' and noWhiteSpace[len(noWhiteSpace) - 1] == '?':
        return 'Sure.'

    return statement




if __name__ == '__main__':
    print(hey('    \t'))
