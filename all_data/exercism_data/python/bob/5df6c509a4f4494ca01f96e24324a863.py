def hey(greeting):

    response = 'Whatever.'

    if greeting.isupper():
        response = 'Woah, chill out!'
    elif greeting.endswith('?'):
        response = 'Sure.'
    elif not greeting.strip():
        response = 'Fine. Be that way!'

    return response
