def hey(msg):
    m = msg.strip()
    if len(m) == 0:
        return 'Fine. Be that way!'
    elif m.isupper() and not m.startswith("Let's go"):
        return 'Whoa, chill out!'
    elif m[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'