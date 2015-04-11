def hey(text):
    """bob.hey is a fuction to speak to bob, be careful what are you going to said him"""
    if text.isspace() or not text:
        return 'Fine. Be that way!'
    elif text.isupper():
        return 'Woah, chill out!'
    elif text.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
