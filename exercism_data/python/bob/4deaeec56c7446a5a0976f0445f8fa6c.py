def hey(sentence):
    if sentence == '':
        return 'Fine. Be that way!'
    isCaps, silent = foobar(sentence)
    if isCaps:
        return 'Woah, chill out!'
    if len(sentence) == silent:
        return 'Fine. Be that way!'
    if sentence[-1] == '?':
        return 'Sure.'
    return 'Whatever.'

def foobar(sentence):
    isCaps = False
    silent = 0
    for letter in sentence:
        if letter.isalpha():
            isCaps = True
            if not letter.isupper():
                isCaps = False
                break
        elif letter == ' ' or letter == '\t':
            silent += 1
    return isCaps, silent
