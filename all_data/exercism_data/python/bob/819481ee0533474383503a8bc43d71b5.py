phrases = {
    '?': 'Sure.',
    '!': 'Woah, chill out!',
    '': 'Fine. Be that way!',
    '*': 'Whatever.',
}

def hey(text):
    text = text.strip()
    if not text:
        k = ''
    elif text.upper() == text and text.lower() != text:
        k = '!'
    elif text.endswith('?'):
        k = '?'
    else:
        k = '*'
    return phrases[k]
