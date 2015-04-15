## Python 3.4.0 (default, Apr 11 2014, 13:05:11) 
## [GCC 4.8.2] on linux

def is_question(sentence):
    return sentence.endswith('?')

def shouting(sentence):
    return sentence.isupper()

def nothing_said(sentence):
    return sentence == ''

def hey(say=''):
    bob_says = 'Whatever.'
    if type(say) == type(''):
        say = say.strip()
        if nothing_said(say):
            bob_says = 'Fine. Be that way!'
        elif shouting(say):
            bob_says = 'Woah, chill out!'
        elif is_question(say):
            bob_says = 'Sure.'
    return bob_says
