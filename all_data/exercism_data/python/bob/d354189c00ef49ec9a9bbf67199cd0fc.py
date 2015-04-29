def hey(text):
    if text.isupper():
        return 'Woah, chill out!'
    elif text and '?' in text[-1]:
        return 'Sure.'
    elif text.isspace() or not text:
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
