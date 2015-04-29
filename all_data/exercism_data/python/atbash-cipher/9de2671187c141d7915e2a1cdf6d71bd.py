import string

alphabet = string.ascii_lowercase
mapping = str.maketrans(alphabet, alphabet[::-1])

def decode(w,size=5):
    return ''.join([u for u in w if u.isalnum()]).translate(mapping)

def encode(w, size=5):
    encoded_w = ''.join( c for c in w.lower() if c.isalnum() ).translate(mapping)
    split_encoded_w = ' '.join([ encoded_w[i:i+size] for i in range(0,len(encoded_w),size) ])
    return split_encoded_w
