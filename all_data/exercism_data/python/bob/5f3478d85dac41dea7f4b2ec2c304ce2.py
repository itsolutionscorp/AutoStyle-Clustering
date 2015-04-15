class Bob:
  
  def hey(self, to_bob):
    # to_bob should be a string representing an expression said to Bob.
    # Remove spaces since they aren't relevant to the logic of Bob's reponse.
    to_bob = to_bob.strip()
    return self.bobs_response(to_bob)

  def bobs_response(self, to_bob):
    # Check if anything meaningful was said to Bob
    if len(to_bob):

      # A string that is all caps indicates yelling
      if to_bob.isupper():
        return 'Woah, chill out!'

      # A string ending in '?' indicates a question.
      elif to_bob.endswith('?'):
        return 'Sure.'

      else:
        return 'Whatever.'

    # Bob doesn't like the silent treatment
    else:
      return 'Fine. Be that way!'
