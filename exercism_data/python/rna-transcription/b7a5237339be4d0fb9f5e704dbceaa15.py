def to_rna(dnaString):
# create local string variable for returning data
   rnaString = ""
# remove preceding and trailing whitespace
   dnaString = dnaString.strip()
# iterate through each letter of string for conversion and repacking   
   for letter in dnaString:
      if letter == "G":
         rnaString += "C"
      elif letter == "T":
         rnaString += "A"
      elif letter == "A":
         rnaString += "U"
      elif letter == "C":
         rnaString += "G"
   return rnaString
