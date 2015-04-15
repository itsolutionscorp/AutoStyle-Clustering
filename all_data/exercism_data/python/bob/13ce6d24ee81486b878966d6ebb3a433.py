import re
def hey(input):
  if(len(input) == 0 or len(input.strip()) == 0):
    return "Fine. Be that way!"
  elif(input.upper() == input and re.search('[a-zA-Z]', input)):
      return "Whoa, chill out!"
  elif(input[len(input)-1] == '?'):
    return "Sure."
  return "Whatever."
