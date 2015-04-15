__author__ = 'Flavio Miranda'
import math
import re


def encode(text):
    ## Remove all non alphanumeric chars [^a-zA-Z0-9] is the negation
    text = re.sub(r'([^a-zA-Z0-9])', '', text)
    if not text: # Empty string after the trimming
         return encoded
    matrix = root(len(text))
    mapp = squaremap(text,matrix)
    return fromsquare(mapp, matrix)


def squaremap(text,tup):
    return [text[i*tup[0]:i*tup[0]+tup[0]] for i in range(tup[1])]


def fromsquare(mat, tup):
    return " ".join(["".join([mat[j][i].lower()  for j in range(tup[1]) if i<len(mat[j])]) for i in range(tup[0])]) #text


def root(size):
    r = math.sqrt(size)
    x = int(r)
    return (x,x) if x==r else (x+1,x) if x*(x+1)>=size else (x+1,x+1)
