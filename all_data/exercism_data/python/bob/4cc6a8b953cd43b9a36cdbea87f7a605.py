def hey(words):

    if len(words.strip())==0:
        return 'Fine. Be that way!'
    elif words == words.upper() and len([i for i in words if i.isalpha()])>0 :
        return 'Whoa, chill out!'
    
    punctuation=words[-1]

    if punctuation=='?':
        return 'Sure.'
    else:
        return 'Whatever.'
