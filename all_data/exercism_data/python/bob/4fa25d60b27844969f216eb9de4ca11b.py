QUESTION_RESPONSE = 'Sure.'
SHOUT_RESPONSE = 'Whoa, chill out!'
SILENCE_RESPONSE = 'Fine. Be that way!'
NEUTRAL_RESPONSE = 'Whatever.'

def hey(sentence):
    '''return the correct response for the sentence'''
    
    #Shout is first so that shouting questions will count
    #as shouting
    if sentence.isupper():
        return SHOUT_RESPONSE
        
    elif sentence.endswith('?'):
        return QUESTION_RESPONSE
        
    elif sentence.strip()=='':
        return SILENCE_RESPONSE
        
    else:
        return NEUTRAL_RESPONSE
    
