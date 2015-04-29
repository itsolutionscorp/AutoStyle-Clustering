def hey(words):
    if len(words) == 0 or words.encode('utf-8').isspace():
        return 'Fine. Be that way!'
    if words.isupper():
        return 'Woah, chill out!'
    if words[-1] == '!':
        return 'Whatever.'
    if words[-1] == '?':
        return 'Sure.'
    return 'Whatever.'
