def is_question(sentence):
    return sentence.endswith('?')

def shouting(sentence):
    return sentence.isupper()

def nothing_said(sentence):
    return sentence == ''

def hey(say=''):
    say = say.strip()
    bob_says = 'Whatever.'
    if nothing_said(say):
        bob_says = 'Fine. Be that way!'
    elif shouting(say):
        bob_says = 'Woah, chill out!'
    elif is_question(say):
        bob_says = 'Sure.'
    return bob_says
