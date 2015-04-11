def hey(what):
    if what.isspace() or what == '':
        return 'Fine. Be that way!'

    flag = False
    for w in what:
        if not w.isalpha():
            continue
        if w.islower():
            break
        flag = True
    else:
        if flag:
            return 'Whoa, chill out!'

    if what.endswith('?'):
        return 'Sure.'
    elif what:
        return 'Whatever.'
