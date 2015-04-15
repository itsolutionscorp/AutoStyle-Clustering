def hey(phrase):
    if len(phrase) == 0 or phrase.isspace():
        return 'Fine. Be that way!'
    if phrase[-1] == '?' and phrase.isupper():
        return 'Whoa, chill out!'
    if phrase[-1] == '?':
        return 'Sure.'
    if phrase.isupper():
        return 'Whoa, chill out!'
    else:
        return 'Whatever.'
