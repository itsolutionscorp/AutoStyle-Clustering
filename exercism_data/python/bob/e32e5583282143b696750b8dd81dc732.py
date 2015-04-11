#
# Skeleton file for the Python "Bob" exercise.
#
import re
def hey(sentence):

    # response to yell
    if sentence.isupper():
        return 'Whoa, chill out!'

    # response to questions
    if re.search('\?\s*$',sentence):
        return 'Sure.'

    # response to silence
    if sentence.strip()=='':
        return 'Fine. Be that way!'

    # default response
    return 'Whatever.'
