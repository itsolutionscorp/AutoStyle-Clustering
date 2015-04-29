class Bob():

  def hey(self, speaker):
    if ( speaker == None or speaker.strip() == '' ):
      return "Fine. Be that way!"
    if speaker.upper() == speaker:
      return "Woah, chill out!"
    if speaker[-1] == "?":
      return "Sure."
    else:
      return "Whatever."
  
