def hey(message):
    mlen = len(message.strip())-1
    if mlen < 1:
        return "Fine. Be that way!"
    if message != message.swapcase() and message == message.upper():
        return "Whoa, chill out!"
    if message[mlen:] == '?':
        return "Sure."
    return "Whatever."
