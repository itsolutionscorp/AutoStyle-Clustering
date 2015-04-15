def hey(s):
   if s.strip() == "":
     return 'Fine. Be that way!'
   if s.isupper():
       return 'Whoa, chill out!'
   if s.endswith("?"): return "Sure."

   return "Whatever."
