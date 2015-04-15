def hey(sentence):
    if is_question(sentence) and is_shouting(sentence) and not is_only_numbers(sentence.replace('?', '')):
        return 'Whoa, chill out!'

    if is_silence(sentence):
        return 'Fine. Be that way!'

    if is_question(sentence):
        return 'Sure.'

    if is_shouting(sentence) and not is_only_numbers(sentence):
        return 'Whoa, chill out!'

    return 'Whatever.'

def is_question(sentence):
    return sentence.endswith('?')

def is_shouting(sentence):
    return sentence == sentence.upper()

def is_silence(sentence):
    return not sentence.strip()

def is_only_numbers(sentence):
    try:
        int(sentence.replace(', ', ''))
        return True
    except ValueError:
        return False
