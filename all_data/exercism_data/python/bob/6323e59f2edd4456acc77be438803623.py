def hey(sentence):
    if sentence.isupper():
        return 'Whoa, chill out!'
    elif sentence.endswith('?'):
        return 'Sure.'
    elif sentence.strip() == '':
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
