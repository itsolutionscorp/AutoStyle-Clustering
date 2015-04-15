import string
import math

def encode(text):
    if len(text) == 0:
        return ''
    
    clean_text = ''.join([char.lower()
                          for char in text
                          if char in (string.ascii_letters+string.digits)])

    col_len = int(math.ceil(math.sqrt(len(clean_text))))
    columns = [clean_text[i:i+col_len] for i in range(0, len(clean_text), col_len)]
    
    rows = ['' for char in columns[0]]
    for i in range(len(rows)):
        for column in columns:
            try:
                rows[i] += column[i]
            except:
                pass

    return ' '.join(rows)
