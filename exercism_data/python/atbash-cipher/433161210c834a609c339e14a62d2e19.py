import string

letters = string.ascii_lowercase

def substitute(letter):
    if letter.lower() in letters:
        return letters[::-1][letters.find(letter)]
    elif letter in string.digits:
        return letter
    else:
        return ""

def decode(text):
    return "".join([substitute(letter) for letter in text.lower()])

def encode(text):
    output_str = decode(text)
    return " ".join([output_str[start:start+5] for start in xrange(0, len(output_str), 5)])
