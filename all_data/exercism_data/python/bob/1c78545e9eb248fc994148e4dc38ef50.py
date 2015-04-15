def hey(words):
    words = words.strip()
    if not words: 
        return 'Fine. Be that way!'
    elif words.isupper():
        return 'Woah, chill out!'
    elif words.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
