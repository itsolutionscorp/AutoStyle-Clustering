HASHED_CHARS = "abcdefghijklmnopqrstuvwxyz"
PRESERVED_CHARS = "0123456789"
HASH_KEY = {x:y for x,y in zip(HASHED_CHARS, HASHED_CHARS[::-1])}
HASH_KEY.update({i:i for i in PRESERVED_CHARS})

def _inject(payload, iterable, every=1):
    it = iter(iterable)
    next = it.next() # pre-fetch before loop to match state at end of loop
    while True:
        yield next
        for j in range(every-1): # `-1` for pre-fetch
            yield it.next()
        next = it.next() # pre-fetch to avoid ending with a payload
        yield payload

def _hash(text):
    return (HASH_KEY[c] for c in text.lower() if c in HASH_KEY)

def encode(text, block_size=5):
    # not sure this is the most readable, but generators are pretty awesome.
    return ''.join(_inject(" ", _hash(text), block_size))

def decode(text):
    return ''.join(_hash(text))
