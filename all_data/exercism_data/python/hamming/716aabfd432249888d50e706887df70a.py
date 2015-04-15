import itertools

def equal(a,b):
  return 0 if (a==b) else 1

def distance(dnaA,dnaB):
  return sum(itertools.imap(equal,dnaA,dnaB))
