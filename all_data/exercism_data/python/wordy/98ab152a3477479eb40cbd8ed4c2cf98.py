import re

def calculate(s):
    n = 0
    pos = 0

    m = re.match('^What is (.*)\?$', s)
    if not m:
        raise ValueError('Input not understood.')
    s = m.group(1)

    s = s.replace(' plus ',' + ')
    s = s.replace(' minus ',' - ')
    s = s.replace(' divided by ',' / ')
    s = s.replace(' multiplied by ',' * ')

    tok = s.split()

    while pos < len(tok) - 2:
        if not (re.match('-?\d+', tok[pos]) and re.match('-?\d+', tok[pos+2])
                and re.match('[+-/*]', tok[pos+1])):
            raise ValueError('Can not parse.')

        n = eval(' '.join(tok[pos:pos+3]))
        pos += 2
        tok[pos] = str(n)

    return n
