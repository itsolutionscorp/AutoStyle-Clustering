def is_question(sentence):
    return sentence.endswith('?')

def to_loud(sentence):
    return sentence.isupper()

def say_nothing(sentence):
    return sentence == ''

def hey(say=''):
    say = say.strip()
    if say_nothing(say):
        return 'Fine. Be that way!'
    if to_loud(say):
        return 'Woah, chill out!'
    if is_question(say):
        return 'Sure.'
    return 'Whatever.'
