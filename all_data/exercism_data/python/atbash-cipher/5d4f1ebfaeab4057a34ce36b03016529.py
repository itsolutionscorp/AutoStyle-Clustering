import string

encoded_alphabet = dict(zip(string.ascii_lowercase, string.ascii_lowercase[::-1]))
decoded_alphabet = dict(zip(string.ascii_lowercase[::-1], string.ascii_lowercase))

def decode(w,size=5):
    unsplit_w = ''.join(u for u in w.split(' '))
    decoded_w =  ''.join(decoded_alphabet[c] for c in unsplit_w)
    return decoded_w

def encode(w, size=5):
    encoded_w = ''.join([encoded_alphabet[c.lower() ] if c.isalpha() else c if c.isdigit() else "" for c in w  ])
    split_encoded_w = ' '.join([ encoded_w[i:i+size] for i in range(0,len(encoded_w),size) ])
    return split_encoded_w
