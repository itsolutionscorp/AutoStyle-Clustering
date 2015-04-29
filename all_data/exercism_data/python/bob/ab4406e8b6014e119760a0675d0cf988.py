class Bob:
  
  def hey(self, mess_bob):
    # strip() method to remove default whitespace characters from the message
    mess_bob = mess_bob.strip()
    return self.response(mess_bob)

  def response(self, mess_bob):
    if len(mess_bob):

      # isupper() method to validate caps meaning yelling
      if mess_bob.isupper():
        return 'Woah, chill out!'

      # validating questions
      elif mess_bob.endswith('?'):
        return 'Sure.'

      else:
        return 'Whatever.'

    # everything else! :)
    else:
      return 'Fine. Be that way!'
