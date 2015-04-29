#
# Skeleton file for the Python "Bob" exercise.
#
import re
def hey(what):

    #default answer
    answer = 'Whatever.'
    if what.isupper():
        answer = 'Whoa, chill out!'
    elif what.strip().endswith('?') > 0:
        answer = 'Sure.'
    elif what == '' or what.isspace():
        answer = 'Fine. Be that way!'

    return answer
