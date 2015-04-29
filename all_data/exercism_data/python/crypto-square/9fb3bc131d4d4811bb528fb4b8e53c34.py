from math import sqrt, ceil

def encode(msg):
    txt = ''.join([s for s in msg.lower() if s.isalnum()])
    segment = int(ceil(sqrt(len(txt))))

    return ' '.join(txt[i::segment] for i in range(segment))
