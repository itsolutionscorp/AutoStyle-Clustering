#!path/python27
# William Morris
# exercism.io
# crypto_square.py

import string
import math

def encode(phrase):
    phrase = phrase.translate(None,string.whitespace + string.punctuation).lower()
    square_size = int(math.ceil(math.sqrt(len(phrase))))
    if phrase == '' or square_size == 0:
        return ''
    rows = [phrase[start:start+square_size]
            for start in range(0,len(phrase),square_size)]
    if len(rows[-1])< square_size:
        rows[-1] = rows[-1].ljust(square_size)
    columns = ['' for i in range(square_size)]
    for i in range(square_size):
        for row in rows:
            columns[i] += row[i]
        columns[i] = columns[i].strip()
    return string.join(columns)
     
            
    
        
