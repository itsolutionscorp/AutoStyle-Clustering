def hey(comment) :    
    if is_shouting(comment) :
        return 'Woah, chill out!'
    elif len(comment) > 0 and comment[-1] == '?' :
        return 'Sure.'
    elif comment.strip() == '' :
        return 'Fine. Be that way!'
    else :
        return 'Whatever.'

def is_shouting(com) :
    at_least_one = False
    for c in com :
        if c != c.upper() :
            return False
        if c.isalpha() :
            at_least_one = True
    return True and at_least_one
