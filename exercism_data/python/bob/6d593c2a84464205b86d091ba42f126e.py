def hey(message):

    # Strip any spaces, check length
    if (len(message.lstrip()) == 0):
        return "Fine. Be that way!"

    # Returns true if all cased characters are upper
    if message.isupper():
        return "Whoa, chill out!"

    if message.endswith('?'):
        return "Sure."
        
    message = message.lstrip()

    # Without spaces, is something being said?
    if message[0].isalnum():
       return "Whatever."
    else:
        return "Fine. Be that way!"

    return
