# Bob

def hey(inp):
    if not inp or len(inp.strip('\n').strip('\t'))==inp.count(' '):
        return 'Fine. Be that way!'
    if inp.upper() == inp and [p for p in inp if p in 'ABCDEFGHIJKLMNOPPQRSTUVWYZ']!=[]:
        return 'Whoa, chill out!'
    if inp[-1]=='?':
        return 'Sure.'
    return 'Whatever.'
