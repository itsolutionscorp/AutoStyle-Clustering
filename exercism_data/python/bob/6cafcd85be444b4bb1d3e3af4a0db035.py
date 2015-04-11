def hey(inp):
    r='Whatever.'
    if not inp or len(inp.strip('\n').strip('\t'))==inp.count(' '):r='Fine. Be that way!'
    elif inp.upper() == inp and [p for p in inp if p in 'ABCDEFGHIJKLMNOPPQRSTUVWYZ']!=[]:r='Whoa, chill out!'
    elif inp[-1]=='?':r='Sure.'
    return r
