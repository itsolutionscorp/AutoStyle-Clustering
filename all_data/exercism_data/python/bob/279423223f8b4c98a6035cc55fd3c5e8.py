class Bob():
   sure = 'Sure.'
   chill = 'Woah, chill out!'
   fine = 'Fine. Be that way!'
   whatever = 'Whatever.'

   def hey(self, text):
      if self.checkFine(text):
         return self.fine
      if self.checkSure(text):
         return self.sure
      if self.checkChill(text):
         return self.chill
      return self.whatever

   def checkSure(self, text):
      if text == None or not text:
         return False
      return not text.isupper() and text[-1] == '?'

   def checkChill(self, text):
      return text.isupper()

   def checkFine(self, text):
      return text == None or not text or text.isspace()
