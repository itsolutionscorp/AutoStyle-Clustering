class Bob:
   def hey(self, s):
      if s is None or s.strip() == '':
          return 'Fine. Be that way.'
      elif s.isupper():
          return 'Woah, chill out!'
      elif s.endswith('?'):
          return 'Sure.'
      else:
          return 'Whatever.'
