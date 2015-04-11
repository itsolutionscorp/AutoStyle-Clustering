import re

def hey(text):
   if re.match("^\s*$", text):
       return "Fine. Be that way!"
   elif re.search("[A-Z]", text) and text.upper() == text:
       return "Whoa, chill out!"
   elif "?" == text[-1]:
       return "Sure."
   return "Whatever."
