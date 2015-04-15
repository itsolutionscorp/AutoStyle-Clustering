import re


from string import punctuation as pct
from string import whitespace as wht

def encode(wds):
    tmp = wds.lower().translate(None, pct+wht).strip(' \t\r\n')
    if len(tmp) < 2:
        return tmp
    elif len(tmp) == 2:
        return tmp[::-1]
    else:
        col = 1
        while col**2 < len(tmp):
            col += 1
        pfs = ''
        x, y, z = (0, 0, 0)
        while x < col:
            y = x
            while y < len(tmp):
                pfs += tmp[y]
                if (len(pfs.strip())-z) % 5 == 0:
                    pfs += ' '
                    z += 1
                y += col
            x += 1
        return pfs.strip()

def decode(wds):
    tmp = wds.translate(None, wht)
    col = 1
    while col**2 < len(tmp):
        col += 1
    row = len(tmp)/col
    if row*col < len(tmp):
        row += 1
    outp = ''
    x, y = (0, 0)
    while len(outp) < len(tmp):
        outp += tmp[y]
        if y >= (row * (col+(len(tmp)-(row*col)))):
            y += row - 1
        else:
            y += row
        if y >= len(tmp):
            x += 1
            y = x
    return outp
    
        

        
