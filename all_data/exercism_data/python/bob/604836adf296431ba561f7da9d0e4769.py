def hey(sentence):
    sentence = sentence.strip()
    is_yelling = sentence.upper() == sentence
    has_alpha = any(s.isalpha() for s in sentence)

    if sentence == '':
        return 'Fine. Be that way!'
    elif sentence[-1] == '?' and (not is_yelling or not has_alpha):
        return 'Sure.'
    elif is_yelling and has_alpha:
        return 'Whoa, chill out!'
    return 'Whatever.'
