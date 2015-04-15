def hey(words):
    if words.isupper():
        return 'Woah, chill out!'
    if words.endswith('?'):
        return 'Sure.'
    # checks for spaces and non-text chars
    if words.isspace() or not words:
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
