import math


def encode(text, group=True):
    text = [c.lower() for c in text if c.isalnum()]
    num_chunks = int(math.ceil(math.sqrt(len(text))))
    chunks = [[] for x in xrange(0, num_chunks)]
    for i, c in enumerate(text):
        chunks[i % num_chunks].append(c)
    text = ''.join(c for chunk in chunks for c in chunk)
    return ' '.join(text[i:i+5] for i in range(0, len(text), 5)) if group else text


def decode(text):
    text = [c.lower() for c in text if c.isalnum()]
    num_chunks = int(math.ceil(math.sqrt(len(text))))
    chunks = [[] for x in xrange(0, num_chunks)]
    extra = len(text) % num_chunks + 1
    i = 0
    for c in text:
        if extra > 0:
            chunks[i % (num_chunks-1)].append(c)
            if i % (num_chunks-1) == 0:
                extra -= 1
                if extra == 0:
                    i = 0
        else:
            chunks[i % (num_chunks-2)].append(c)
        i += 1
    return ''.join(c for chunk in chunks for c in chunk)
