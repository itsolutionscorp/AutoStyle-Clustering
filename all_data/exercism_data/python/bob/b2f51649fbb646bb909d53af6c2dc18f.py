def hey(input_text):
    if input_text.isupper():
        return 'Woah, chill out!'
    elif input_text.endswith('?'):
        return 'Sure.'
    elif input_text.isspace() or input_text == '':
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
