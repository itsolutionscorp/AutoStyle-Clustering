# -*- coding: utf8 -*-

class Bob:

  def hey(self, message):
    if not message :
      return self.FINE_BE_THAT_WAY
    
    message = message.decode('cp1252')
    
    if not message.strip():
      return self.FINE_BE_THAT_WAY
    if message.isupper():
      return self.WOAH_CHILL_OUT
    elif message.endswith("?"):
        return self.SURE
 
    return self.WHATEVER


  FINE_BE_THAT_WAY = "Fine. Be that way!"
  WOAH_CHILL_OUT = "Woah, chill out!"
  SURE = "Sure."
  WHATEVER = "Whatever."
