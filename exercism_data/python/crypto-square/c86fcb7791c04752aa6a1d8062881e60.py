from math import ceil

def encode(st):
    s = ''
    for c in st.lower():
        if c.isalpha() or c.isdigit():
            s += c

    sz = int(ceil(len(s) ** 0.5))
    array = [''] * sz

    for i,c in enumerate(s):
        array[i % sz] += c

    return ' '.join(array)
