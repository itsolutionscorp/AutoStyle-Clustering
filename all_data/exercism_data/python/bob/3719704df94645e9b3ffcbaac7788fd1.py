""" Exercise 1 """
import re

def hey(question):
    """ takes in the question, returns the response """
    if not question.strip():
        return "Fine. Be that way!"
    if question == question.upper():
        if re.search('[a-zA-Z]', question):
            return "Whoa, chill out!"
    if question[-1:] == '?':
        return 'Sure.'
    return 'Whatever.'
