#Exercism -> bob

def hey(sentence):
    '''Say something to bob'''

    # Bob will answer 'Whoa, chill out!' to anything forceful (i.e full uppercase)
    if sentence.isupper():
        return 'Whoa, chill out!'

    # Bob will answer 'Sure.' to any basic question
    if sentence.endswith('?'):
        return 'Sure.'

    # Bob will answer 'Fine. Be that way!' if the sentence is empty or not alphanumerical
    if sentence.isspace() or sentence == '':
        return 'Fine. Be that way!'

    # Bob will answer 'Whatever.' to any uncaught cases
    return 'Whatever.'
