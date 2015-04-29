lowerletters = "qwertyuiopasdfghjklzxcvbnm"
upperletters=lowerletters.upper()
letters=lowerletters+upperletters


def isaletter(letter):
 return (letter in letters)

def transform(letter):
 if letter == 'G':
  return 'C'
 if letter == 'C':
  return 'G'
 if letter  == 'T':
  return 'A'
 if letter == 'A':
  return 'U'
 else:
  return letter


def to_rna(sequence):
 cleaned=filter(isaletter, sequence)
 if sequence != cleaned:
  return "Invalid sequence"
 if cleaned != cleaned.upper():
  return "Uppercase only!"
 return ''.join(map(transform, cleaned))
