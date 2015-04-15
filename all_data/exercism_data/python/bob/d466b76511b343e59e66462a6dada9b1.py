class Bob:
  def hey(placeholder,input):
    if input =='' or input.isspace():
      return "Fine. Be that way!"
    elif input.isupper():
      return "Woah, chill out!"
    elif input[-1]=='?':
      return "Sure."
    else:
      return "Whatever."
    
 
    
