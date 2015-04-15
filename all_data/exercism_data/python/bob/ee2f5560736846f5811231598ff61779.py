def hey(foo):
    if foo == '' or onlySpace(foo):
        return 'Fine. Be that way!'
    elif (not shouting(foo) or contNum(foo)) and asking(foo):
        return 'Sure.'
    elif shouting(foo):
        return 'Whoa, chill out!'
    else:
        return 'Whatever.'

def shouting(foo):
    return foo.upper() == foo and not onlyNum(foo)

def asking(foo):
    return foo[len(foo)-1] == '?'

def contNum(foo):
    for i in foo:
        return i.isnumeric()

def onlyNum(foo):
    for i in foo.replace(',','').replace(' ',''):
        if not i.isnumeric():
             return False
    return True

def onlySpace(foo):
    for i in foo:
        if not i.isspace():
            return False
    return True
