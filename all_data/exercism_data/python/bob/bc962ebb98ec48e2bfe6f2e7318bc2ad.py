import re

def hey(a_string):
    # First, consider the empty string, and the one composed
    # only by whitespaces.
    if not a_string or re.search('^\s+$', a_string):
        return 'Fine. Be that way!'
    # Now the yelling. Lasta character is a ! or it's only written
    # in capital characters.
    elif a_string[-1] == '!' or re.search('^[A-Z]+$', a_string.replace(' ','')):
        if a_string.upper() == a_string:
            return 'Woah, chill out!'
        # This eliminates the 'talk forcefully' exception
        else:
            return 'Whatever.'
    # The questions
    elif a_string[-1] == '?':
        if a_string.upper() == a_string and not re.search('^\d+\?', a_string):
            return 'Woah, chill out!'
        # Get rid of the 'only numbers' question exception
        else:
            return 'Sure.'
    else:
        return 'Whatever.'
