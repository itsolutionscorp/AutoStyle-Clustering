import string, math

def encode(text):
    text = _normalize(text)
    col = _getcol(len(text))
    
    secret = ''
    for start in xrange(col):
        secret += ''.join((text[start::col]))
    return _grouptext(secret)
    
def decode(text):
    
    text = _normalize(text)
    textlen = len(text)
    
    row = _getcol(textlen)
    col = _getrow(textlen)
    
    # Cipher text must have additional spacing added to line up correctly.
    # !
    # Break up cipher text into two pieces determined by column completeness
    fullcols = textlen % row
    piece1 = text[0:fullcols*col]
    piece2 = text[len(piece1):]
    
    # Divide the remaining pieces into smaller segments and add a space to each segment
    numshortcols = row - fullcols
    shortcolsize = len(piece2) / numshortcols
    for x in xrange(numshortcols):
        start = x * shortcolsize
        end = start + shortcolsize
        piece1 += piece2[start:end] + ' '
        
    secret = ''
    for start in xrange(col):
        secret += ''.join((piece1[start::col]))
        
    return _normalize(secret)
    
    
def _grouptext(text, blocksize=5):
    """Groups the output of encode into blocks of a given size"""
    return " ".join(text[i:(i + blocksize)]
            for i in xrange(0, len(text), blocksize))
    

    
def _getcol(length):
    return int(math.ceil(math.sqrt(length)))

def _getrow(length):
    return int(math.ceil(float(length) / _getcol(length)))

def _normalize(text):
    return ''.join([letter for letter in text.lower() \
                if letter.isalpha() or letter.isdigit()])
