def hey(message):
    response = "Whatever."

    if message.isupper():
        response = "Whoa, chill out!"
    elif not message.strip():
        response = "Fine. Be that way!"
    elif message.endswith('?'):
        response = "Sure."
    
    return response
