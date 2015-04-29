def hey(message):
    alphanum = [c for c in message if c.isalnum()]
    lowercase = [c for c in message if c.islower()]
    uppercase = [c for c in message if c.isupper()]

    if '' == message:
        return 'Fine. Be that way!'
    if len(alphanum) == 0:
        return 'Fine. Be that way!'
    if len(lowercase) == 0 and len(uppercase) > 0:
        return 'Woah, chill out!'
    if message.endswith("?"):
        return 'Sure.'
    return 'Whatever.'

print(hey('Tom-ay-to, tom-aaaah-to.'))
