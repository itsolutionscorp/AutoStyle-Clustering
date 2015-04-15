import math

def encode(msg):
    
    normalized = msg.lower().translate(None, ".,!' ")
    numchars = len(normalized)
    if numchars == 0:
        return ''

    numcolumns = int(math.ceil(math.sqrt(numchars)))
    numrows = int(math.ceil(float(numchars)/numcolumns))
    
    encoded = ''
    for i in range(numcolumns):
        for j in range(numrows):
            index = i + j*numcolumns
            if index < numchars:
                encoded += normalized[index]
        encoded += ' '

    encoded = encoded.strip()

    return encoded
