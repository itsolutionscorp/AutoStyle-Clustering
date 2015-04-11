import string

def clean_input( w ):
    wClean = ''
    for l in w:
        if l.lower() in string.ascii_lowercase:
            wClean += l.lower()
    return wClean

def value( l ):
    if l in ['a','e','i','o','u','l','n','r','s','t']:
        return 1
    elif l in ['d','g']:
        return 2
    elif l in ['b','c','m','p']:
        return 3
    elif l in ['f','h','v','w','y']:
        return 4
    elif l in ['k']:
        return 5
    elif l in ['j','x']:
        return 8
    elif l in ['q','z']:
        return 10
    else:
        raise ValueError( 'Bad letter in value(l)!' )

def score( w ):
    w = clean_input( w )
    my_score = 0
    for l in w:
        my_score += value( l )
    return my_score
