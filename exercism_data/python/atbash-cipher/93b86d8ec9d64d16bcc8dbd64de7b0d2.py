import re

alpha = list('abcdefghijklmnopqrstuvwxyz')
encode_dict = dict(zip([ord(x) for x in alpha], reversed(alpha)))
decode_dict = dict(zip([ord(x) for x in reversed(alpha)], alpha))

def encode(st):
    r = re.sub('[ .,;:\'\"\\/!?]', '', st.lower())
    r = r.translate(encode_dict)
    return ' '.join([r[i:i+5] for i in range(0, len(r), 5)])

def decode(st):
    return st.replace(' ', '').translate(decode_dict)
