class Bob():
   sure = 'Sure.'
   chill = 'Woah, chill out!'
   fine = 'Fine. Be that way!'
   whatever = 'Whatever.'

   def hey(self, text):
      if text == None or not text or text.isspace():
         return self.fine

      if text != None and not text.isspace():
         if not text.isupper() and text[-1] == '?':
            return self.sure

      if text.isupper():
         return self.chill

      return self.whatever
