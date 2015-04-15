import re


def hey(phrase):
    ''' Decides how Bob will respond. '''

    # Handle unicode strings
    try:
        phrase = phrase.decode('ascii')
    except:
        m = phrase.encode('utf8')
        if m.decode('utf8').isupper():
            return 'Whoa, chill out!'
        elif re.match(r'\?$', m):
            return 'Sure.'
        else:
            return 'Whatever.'

    # If you don't say anything
    # - Phrase doesn't contain any letters or numbers
    if not re.findall(r'\w', phrase):
        return 'Fine. Be that way!'

    # If you yell at him
    # - All letters are caps
    if phrase.isupper():
        return 'Whoa, chill out!'

    # If you ask something
    # - The phrase contains a question mark at the end
    if re.search(r'\?$', phrase):
        return 'Sure.'

    # In any other case
    return 'Whatever.'
