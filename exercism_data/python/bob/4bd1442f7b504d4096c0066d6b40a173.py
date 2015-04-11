class Bob(object):


   sure = 'Sure.'
   chill = 'Woah, chill out!'
   fine = 'Fine. Be that way!'
   whatever = 'Whatever.'

   def hey(self, text):

      if text is not None:
         text = text.strip()
      else:
         return self.fine

      if not text:
         return self.fine

      if text.isupper():
         return self.chill

      if text.endswith('?'):
         return self.sure

      return self.whatever
