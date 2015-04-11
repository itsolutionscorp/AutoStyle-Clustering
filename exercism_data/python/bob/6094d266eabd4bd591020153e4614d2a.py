def hey(message):
    message = ' '.join(message.split())
    if is_yelling(message):
        return "Whoa, chill out!"
    if is_asking(message):
        return "Sure."
    if is_quiet(message):
        return "Fine. Be that way!"
    else:
        return 'Whatever.'


def is_yelling(msg):
    return msg.isupper()


def is_asking(msg):
    return msg.endswith('?', 1)


def is_quiet(msg):
    return True if len(msg) == 0 else False
