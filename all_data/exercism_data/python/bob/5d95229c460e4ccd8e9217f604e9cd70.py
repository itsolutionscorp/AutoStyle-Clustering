def hey(text):
    if len(text.strip()) == 0:
        return 'Fine. Be that way!'
    if text.isupper():
        return 'Whoa, chill out!'
    if text[-1] == '?':
        return 'Sure.'
    return 'Whatever.'
