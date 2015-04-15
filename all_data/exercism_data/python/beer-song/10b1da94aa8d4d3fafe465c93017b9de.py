def song(start, end=0):
    return ''.join(map(lambda n: verse(n) + "\n", 
                       range(start, end-1, -1)))

def verse(n): return first_line(n) + second_line(n)

def plural(n):  return ('', 's')[n!=1]
def pronoun(n): return ('it', 'one')[n>1]
def nomore(s):  return ('no more', 'No more')[s]
def num(n, s):  return (nomore(s), str(n))[n>0]

def beer(n, s):      return num(n, s) + " bottle{s} of beer".format(s=plural(n))
def beerwall(n, s):  return beer(n, s) + " on the wall"
def takedown(n): return "Take {} down and pass it around".format(pronoun(n))
def gostore():   return "Go to the store and buy some more"

def line(first, second): return first + ", " + second + ".\n"

def takebeer(n): return line(takedown(n), beerwall(n-1, 0))
def buymore():   return line(gostore(),   beerwall(99, 0))

def first_line(n):  return line(beerwall(n, 1), beer(n, 0))
def second_line(n): return (buymore(), takebeer(n))[n>=1]
