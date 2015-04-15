# -*- coding: utf8 -*-

class Bob:
  def hey(self, message):
    if not message:
      return "Fine. Be that way!" 
    
    message = message.decode('cp1252')
    
    if not message.strip():
      return "Fine. Be that way!"
    if message.isupper():
      return "Woah, chill out!"
    elif message.endswith("?"):
        return "Sure."
 
    return "Whatever."
