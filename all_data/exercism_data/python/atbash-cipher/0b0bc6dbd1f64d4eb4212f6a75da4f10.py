__author__ = 'jimblackler'


def chunks(l, n):
    for i in xrange(0, len(l), n):
        yield l[i:i + n]


def encode(msg):
    return ' '.join(chunks(decode(msg), 5))


def decode(msg):
    clean_msg = ''.join([c for c in msg.lower() if c.isalpha() or c.isdigit()])
    return ''.join([chr(ord('a') + ord('z') - ord(c)) if c.isalpha() else c for c in clean_msg])
