def hey(msg):
    msg = msg.strip()
    if not msg:
        # address Bob without saying anything
        return('Fine. Be that way!')
    if msg.isupper():
        # we yelled at Bob
        return('Whoa, chill out!')
    if msg[-1] == '?':
        # we asked Bob a question
        return('Sure.')
    # Bob replies 'Whatever.' to anything else
    return('Whatever.')
