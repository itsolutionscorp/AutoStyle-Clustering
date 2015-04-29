from string import maketrans

def score(word):

  return sum([ int(d) if int(d) > 0 else 10 for d in word.lower().strip().translate(maketrans("abcdefghijklmnopqrstuvwxyz", "13321424185131130111144840")) ])
