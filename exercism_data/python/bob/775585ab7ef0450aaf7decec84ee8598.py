def isQuestion(s):
   if s[-1] == '?':
      return True
   return False

def isSilence(s):
   for c in s:
      if c != ' ':
         return False
   return True

def isShouting(s):
   for c in s:
      if ord('a') <= ord(c) <= ord('z'):
         return False
   return True

class Bob:
   def hey(self, s):
      if isShouting(s):
          return 'Woah, chill out!'
      elif isQuestion(s):
          return 'Sure.'
      elif isSilence(s):
          return 'Fine. Be that way.'
      else:
          return 'Whatever.'
