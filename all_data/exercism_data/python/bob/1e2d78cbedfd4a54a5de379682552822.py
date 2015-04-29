def isQuestion(s):
   return s.endswith('?')

def isSilence(s):
   return s == None or s == '' or s.isspace()

def isShouting(s):
   for c in s:
      if c.islower():
         return False
   return True

class Bob:
   def hey(self, s):
      if isSilence(s):
          return 'Fine. Be that way.'
      elif isShouting(s):
          return 'Woah, chill out!'
      elif isQuestion(s):
          return 'Sure.'
      else:
          return 'Whatever.'
