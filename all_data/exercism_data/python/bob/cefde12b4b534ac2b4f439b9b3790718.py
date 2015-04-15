import re
import unicodedata

#regexp that finds any lowercase letters
lowercase=re.compile(r'[a-z]')

#regexp that finds any uppercase letters
uppercase=re.compile(r'[A-Z]')

#regexp that finds any non-whitespace characters
blank_regexp=re.compile(r'[^\s\t]')

def hey(input):

  #Replace unicode characters with their non-unicode equivalents.
  #Retains case
  filtered_input=unicodedata.normalize('NFKD',input)

  #If the normalized input contains no lowercase letters AND contains uppercase
  if lowercase.search(filtered_input) is None and uppercase.search(filtered_input) is not None:
    return 'Whoa, chill out!'

  #if the last character of the input is a ?
  if input.endswith('?'):
    return 'Sure.'

  #If the input contains no non-whitepsace characters
  if blank_regexp.search(input) is None:
    return 'Fine. Be that way!'

  #everything else
  else:
    return 'Whatever.'

    
