#
# Skeleton file for the Python "Bob" exercise.
#
import sys
import re

#yelling = re.compile('^[^a-z]+$')
question = re.compile('^.*?\?\s*$')
sayingNothing = re.compile('^\s*$')


def hey(what):
    if what.isupper():
        return 'Whoa, chill out!'
    elif re.match(question, what):
        return 'Sure.'
    elif re.match(sayingNothing, what):
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
    return

if __name__ == '__main__':
    print hey(sys.argv[1])
