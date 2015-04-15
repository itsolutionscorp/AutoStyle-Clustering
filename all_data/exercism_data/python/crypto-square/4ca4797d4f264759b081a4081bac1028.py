import string
from math import ceil, floor



def remove_punct(instring):
    outstring = string.lower(instring)
    for char in string.punctuation + ' ':
        outstring = outstring.replace(char, '')
    return outstring
	

def encode(instring):
    """
    Encodes a string with the rectangle cipher
    """
    
    if not instring:
        return ''
    
    instring = remove_punct(instring)
    width = int(ceil(len(instring)**(0.5)))
    output = []
    buffer = ''
    
    for i in range(width):
        for index in range(len(instring)):
            if index % width == i:
                buffer += instring[index]
            
            if len(buffer) == 5:
                output.append(buffer)
                buffer = ''
    if buffer:
        output.append(buffer)
    
    return ' '.join(output)


def decode(instring):
    """
    Decodes a string with the rectangle cipher
    """
    
    instring = remove_punct(instring)
    width = int(ceil(len(instring)**(0.5)))
    height = int(ceil(len(instring)/width))
    offset = 0
    words = []
    output = ''
    
    for n in range(width):
        if n < width - ((width**2-len(instring)) % width):
            words.append(instring[offset:height+1+offset])
            offset += height+1
        else:
            words.append(instring[offset:height+offset])
            offset += height
    
    for column in range(len(words[0])):
        for row in words:
            try:
                output += row[column]
            except:
                pass
            
    
    return output
