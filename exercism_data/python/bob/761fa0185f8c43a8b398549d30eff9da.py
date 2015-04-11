def hey(sentence):
    sentence = sentence.strip()
    if not sentence:
        return 'Fine. Be that way!'
    elif sentence.isupper():
        return 'Whoa, chill out!'
    elif sentence[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
