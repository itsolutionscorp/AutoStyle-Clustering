import re

class Bob():
    def hey(self, str):
       if (len(str.strip()) == 0):
          r = "Fine. Be that way!"
       elif (str.upper() == str and str.lower() != str):
          r = "Woah, chill out!"
       elif (str[-1] == '?'):
          r = "Sure."
       else:
          r = "Whatever."
       return r
