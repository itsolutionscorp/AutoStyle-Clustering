import re

class Bob():
  def hey(self, msg):

    if msg == None or msg.strip() == '':
      return "Fine. Be that way!"

    uppercase_msg = "".join([x.upper() for x in msg])
    if re.search("[A-Z]", msg) and msg == uppercase_msg:
     return "Woah, chill out!"

    if re.search("\?$", msg.strip()):
      return "Sure."

    return "Whatever."
