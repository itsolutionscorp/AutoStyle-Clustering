
def hey(said):
 if len(said.strip())==0:
  return 'Fine. Be that way!'
 if said.isupper():
  return 'Whoa, chill out!'
 if said.strip().endswith('?'):
  return 'Sure.'
 return 'Whatever.'
  
