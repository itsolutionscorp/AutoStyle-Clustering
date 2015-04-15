"""
Bob chatbot
"""

def hey(in_string):
    if in_string.isspace() or not in_string:
        return 'Fine. Be that way!' 
    if in_string.isupper():
        return 'Woah, chill out!'
    if in_string[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
