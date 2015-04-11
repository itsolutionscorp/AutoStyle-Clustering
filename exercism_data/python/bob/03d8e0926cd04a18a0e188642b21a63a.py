def hey(command):
    if command.strip() == '':
        response = 'Fine. Be that way!'
    elif command.isupper():
        response = 'Whoa, chill out!'
    elif command.endswith('?'):
        response = 'Sure.'
    else:
        response = "Whatever."
    return response
