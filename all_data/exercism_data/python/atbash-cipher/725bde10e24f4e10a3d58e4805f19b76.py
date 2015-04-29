'''exer atbash_cipher'''

PLAIN = 'abcdefghijklmnopqrstuvwxyz0123456789'
CIPHER = 'zyxwvutsrqponmlkjihgfedcba0123456789'

def encode(buf):
    '''encode text in buf'''
    xlator = make_translator(PLAIN, CIPHER)
    buf = buf.lower()       # normalize the buffer
    cypher = ''
    counter = 0
    for char in buf:
        cchar = xlator.get(char, '')    # if known, xlate otherwise ignore
        cypher += cchar
        if cchar:                       # handle 5chars pad 5chars pad
            counter += 1                # how many chars actually encoded
            if counter % 5 == 0:        # every 5 encoded chars
                cypher += ' '           # insert pad
    return cypher.strip()               # clean any dangling padding

def decode(buf):
    '''decode cypher in buf'''
    xlator = make_translator(CIPHER, PLAIN)
    buf = buf.replace(' ', '')          # strip embedded padding
    clear = ''
    for char in buf:
        clear += xlator[char]

    return clear

def make_translator(this, that):
    '''build the lookup mechanism'''
    return dict(zip(this, that))
