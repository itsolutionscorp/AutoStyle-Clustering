actions = ['wink', 'double blink', 'close your eyes', 'jump']

def handshake(inputval):

    if type(inputval) is str:
        try:
            inputval = int(inputval,2)
        except ValueError:
            inputval = 0

    if inputval < 0:
        inputval = 0

    commands = [action for action in actions
                if inputval & (2**actions.index(action))]

    if inputval & 16:
        commands = commands[::-1]

    return commands

def code(inputlist):

    output = 0
    for action in inputlist:
        if action not in actions:
            return '0'
        else:             
            output += 2**actions.index(action)
    
    if len(inputlist)>1 and \
       actions.index(inputlist[0])>actions.index(inputlist[1]):
            output += 16        
        
    return str(bin(output))[2:]
