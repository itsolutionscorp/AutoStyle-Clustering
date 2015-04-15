# "Crypto Square" for exercism.io

from string import ascii_lowercase as as_lc
from string import digits

def encode(message:str):
    message = normalize(message)
    sl = len(message)**0.5
    sl = int(sl if int(sl)==sl else sl+1)  # length of slices
    return " ".join(message[idx::sl] for idx in range(sl))

def normalize(text):
    chrmap = dict(zip(as_lc, as_lc))
    chrmap.update(zip(digits,digits))
    return "".join(chrmap.get(c.lower(),'') for c in text)
