def hey(message):
    if message.strip() == '':
        return 'Fine. Be that way!'
    elif message.isupper():
        return 'Whoa, chill out!'
    elif message.rstrip()[-1] == "?":
        return 'Sure.'
    return 'Whatever.''
