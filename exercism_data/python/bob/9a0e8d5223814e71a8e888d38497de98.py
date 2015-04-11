def hey(phrase):
    """Returns Bob's response to a string"""

    phrase = phrase.strip()

    if phrase.isupper():
        return 'Whoa, chill out!'
    elif phrase.endswith('?'):
        return 'Sure.'
    elif not phrase:
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
