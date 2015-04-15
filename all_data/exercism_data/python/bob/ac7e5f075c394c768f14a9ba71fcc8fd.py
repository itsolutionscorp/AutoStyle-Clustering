def hey(s):
    
    if not s or s.isspace():
        return 'Fine. Be that way!'
    
    elif s.isupper():
        return 'Whoa, chill out!'
    
    elif list(s)[-1] == '?' or not s:
        return 'Sure.'
    
    return 'Whatever.'
