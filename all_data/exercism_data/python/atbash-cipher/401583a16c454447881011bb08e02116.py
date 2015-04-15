import string

def encode(str):
    
    #strip punctuation/spaces and make lowercase
    str = filter(lambda c:c.isalnum(),str.lower())
    
    #translate
    forw = string.lowercase + string.digits
    back = string.lowercase[::-1] + string.digits
    coded = ''.join(map(lambda c:back[forw.index(c)],str))
    
    #split into groups of 5, add spaces and join
    grouped = [coded[i:i+5] for i in xrange(0,len(str),5)]
    grouped = [word + ' ' for word in grouped]
    grouped = string.rstrip(''.join(grouped))
    
    return grouped

def decode(str):
    #eliminate strings
    str = filter(lambda c:c!=' ', str)
    
    #translate
    forw = string.lowercase + string.digits
    back = string.lowercase[::-1] + string.digits
    decoded = ''.join(map(lambda c:forw[back.index(c)],str))
    
    return decoded
