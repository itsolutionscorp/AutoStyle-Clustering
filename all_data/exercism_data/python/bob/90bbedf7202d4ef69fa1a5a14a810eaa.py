#Exercism -> bob

def hey(sentence):
    '''Say something to bob'''

    # Bob will answer 'Fine. Be that way!' if the sentence is empty
    if not sentence.strip():
        return 'Fine. Be that way!'

    # Bob will answer 'Whoa, chill out!' to anything forceful (i.e full uppercase)
    elif sentence.isupper():
        return 'Whoa, chill out!'

    # Bob will answer 'Sure.' to any basic question
    elif sentence.endswith('?'):
        return 'Sure.'

    # Bob will answer 'Whatever.' to any uncaught cases
    return 'Whatever.'
