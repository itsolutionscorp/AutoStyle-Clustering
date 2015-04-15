class bob:
   def hey(inputString):
      inputString = inputString.replace(",","").replace(".","").replace("!","").replace(";","").replace(" ","").replace("\t","").replace("\n","")
      print(inputString)
      if len(inputString) < 1:
         return "Fine. Be that way!"
      elif inputString[0:len(inputString) - 1].isdigit() and inputString[len(inputString)-1] == "?":
         return "Sure."
      elif inputString.isdigit():
         return "Whatever."
      elif inputString.upper() == inputString:
         return "Whoa, chill out!"
      elif inputString[len(inputString)-1] == "?":
         return "Sure."
      else:
         return "Whatever."
