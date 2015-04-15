from string import maketrans, punctuation

def decode(str):
    alpha = "abcdefghijklmnopqrstuvwxyz"
    alphaprime = alpha[::-1]
    trans = maketrans(alpha, alphaprime)
    str = str.replace(' ','')
    
    return str.translate(trans)


def encode(str):
    output = []
    gpsize = 5
    alpha = "abcdefghijklmnopqrstuvwxyz"
    alphaprime = alpha[::-1]
    exclude = set(punctuation)
    
    str = str.lower()
    str = ''.join(ch for ch in str if ch not in exclude)
    str = str.replace(' ', '')
    
    trans = maketrans(alpha, alphaprime)
    
    str = str.translate(trans)
    if len(str) > gpsize:     
        for i in xrange(0,len(str),gpsize):
            output.append(str[i:i+gpsize])
        return ' '.join(output)
    else:
        return str
