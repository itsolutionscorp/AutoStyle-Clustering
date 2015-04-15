def hey(what):
    strippedText = what.strip()
    if strippedText.isupper():
        return 'Whoa, chill out!'
    if not strippedText:
        return 'Fine. Be that way!'
    if strippedText.endswith('?'):
        return 'Sure.'
    return "Whatever."
