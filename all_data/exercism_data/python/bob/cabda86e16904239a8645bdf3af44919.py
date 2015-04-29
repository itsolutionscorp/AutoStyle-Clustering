def hey(str):
    shout = False
    question = False
    saidnothing = False
    nomatch = False

    if (str):
        if str.isupper():
            shout = True 
        elif str[-1]=='?': 
            question = True
        elif '\t' in str.strip(' ') or '\r' in str.strip(' '):
            saidnothing = True
        else:
            nomatch = True
    else:
        saidnothing = True

    if shout:
       return 'Woah, chill out!'
    if nomatch:
       return 'Whatever.' 
    if saidnothing:
       return 'Fine. Be that way!'
    if question:
       return 'Sure.'
