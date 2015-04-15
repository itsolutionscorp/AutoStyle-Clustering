def hey(sentence):
    if sentence.strip() == '':
        return 'Fine. Be that way!'
    if sentence.isupper():
        return 'Whoa, chill out!'
    if sentence.endswith('?'):
        return 'Sure.'
    return 'Whatever.'
