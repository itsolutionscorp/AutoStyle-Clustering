def song(start, end=0):
    return ''.join(map(lambda n: verse(n) + '\n', 
                       range(start, end-1, -1)))

def verse(n):
  line = "{0} of beer on the wall, {1} of beer.\n" + \
         "{2}, {3} of beer on the wall.\n"
  return line.format(firstbeer(n, True), firstbeer(n, False), 
                     action(n), secondbeer(n))

def firstbeer(n, top):
  if n>0: return str(n) + " bottle{0}".format('s' if n>1 else '')
  return "{0}o more bottles".format('N' if top else 'n')

def secondbeer(n):
  if n>1: return str(n-1) + " bottle{0}".format('s' if n>2 else '')
  if n==1: return "no more bottles"
  return "99 bottles"
  
def action(n):
  if n>0:  return "Take {0} down and pass it around".format('it' if n==1 else 'one')
  return "Go to the store and buy some more"
