def hey(what):
    if len(what) == 0 or what[len(what)-1] == '\t' or what == ' ':
        return 'Fine. Be that way!'
    x = 1
    while what[len(what)-x] == ' ':
        x += 1
    try:
        float(what[0])
        if what[len(what)-1] == '!':
            return 'Whoa, chill out!'
    except ValueError:
        if what == what.upper():
            return 'Whoa, chill out!'
    if what[len(what)-x] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
