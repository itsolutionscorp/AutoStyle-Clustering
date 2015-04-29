# Feedback http://exercism.io/submissions/98b56cad59f0a91c21729000
def hey(text):
    text = text.strip()
    if not text:
        return 'Fine. Be that way!'
    elif text.isupper():
        return 'Whoa, chill out!'
    elif text[-1] == '?':
        return 'Sure.'
    return 'Whatever.'
