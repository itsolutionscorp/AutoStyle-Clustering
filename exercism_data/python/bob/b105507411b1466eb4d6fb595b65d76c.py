def hey(words):
    #Constants
    QUESTION_SYM = '?'
    SILENCE = 0
    SHOUT = 1
    DEFAULT = 3
    
    responses = {QUESTION_SYM : 'Sure.' , SILENCE : 'Fine. Be that way!', SHOUT : 'Whoa, chill out!', DEFAULT : 'Whatever.' }
    #strip trailing / leading zeros
    words = words.strip()

    response = DEFAULT

    #if the string is empty or it contained only whitespace chars. 
    if len(words) == 0:
        response = SILENCE

    elif(words.isupper()):
        response = SHOUT

    #if it is one of the predefined cases - currently only one case
    elif words[-1] in responses:
        response = words[-1]

    return responses[response]
