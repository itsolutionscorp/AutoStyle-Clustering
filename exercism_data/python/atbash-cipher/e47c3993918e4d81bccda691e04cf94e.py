import string
alpha_rev = string.ascii_lowercase[::-1]


def decode(msg):
    return msg.translate(str.maketrans(alpha_rev, string.ascii_lowercase, string.whitespace))


def encode(msg):
    code = msg.lower().translate(str.maketrans(string.ascii_lowercase, alpha_rev, string.whitespace + string.punctuation))
    #format with spaces every 5 characters
    return ' '.join([code[i:i+5] for i in range(0, len(code), 5)])
