def hey(sentence):
    if sentence.isspace() or not sentence:
        return 'Fine. Be that way!'
    elif sentence.isupper():
        return 'Woah, chill out!'
    elif sentence.endswith('?'):
        return 'Sure.'
    return "Whatever."
