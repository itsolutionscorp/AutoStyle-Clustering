def hey(words):

    if len(words.strip())==0:
        return 'Fine. Be that way!'
    elif words.isupper():
        return 'Whoa, chill out!'
    
    punctuation=words[-1]

    if punctuation=='?':
        return 'Sure.'
    else:
        return 'Whatever.'
