def hey(phrase):
    if phrase.isupper():
        return 'Woah, chill out!'
    elif phrase.endswith('?'):
        return 'Sure.'
    elif phrase.isspace() or not phrase:
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
