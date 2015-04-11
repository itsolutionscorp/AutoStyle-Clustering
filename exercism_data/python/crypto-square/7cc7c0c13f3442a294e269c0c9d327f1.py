from math import sqrt, ceil

def encode(msg):
    txt = ''.join([s for s in msg.lower() if (s.isalpha() or s.isdigit())])
    segment = int(ceil(sqrt(len(txt))))

    result = []
    for i in range(segment):
        for j in range(segment):
            item = i+j*segment
            if item < len(txt):
                result.append(txt[item])
        result.append(' ')

    return ''.join(result).strip()
