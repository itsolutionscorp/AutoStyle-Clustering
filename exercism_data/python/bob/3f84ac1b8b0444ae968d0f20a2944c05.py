#
# Skeleton file for the Python "Bob" exercise.
#
lowerletters="qwertyuiopasdfghjklzxcvbnm"
upperletters="qwertyuiopasdfghjklzxcvbnm".upper()
letters=lowerletters+upperletters
 
def isaletter(letter):
 return (letter in letters)

def hey(what):
 if (what.isspace() == True) or (what==''):
  return "Fine. Be that way!"
 if what==what.upper() and (filter(isaletter,what) != ""):
  return "Whoa, chill out!"
 if (what.strip()).endswith('?'):
  return "Sure."
 else:
  return "Whatever."
