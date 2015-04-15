class Bob:

  def hey(self, what_I_say_to_bob):
    # Spaces are meaningless. Remove them.
    what_I_say_to_bob = what_I_say_to_bob.strip()

    if len(what_I_say_to_bob) > 0:

      if ( what_I_say_to_bob.upper() == what_I_say_to_bob 
           and what_I_say_to_bob.lower() != what_I_say_to_bob ):
        return self.when_yelled_at()

      elif what_I_say_to_bob[-1] == '?':
        return self.when_asked_a_question()

      else:
        return self.default_response()

    elif len(what_I_say_to_bob) == 0:
      return self.when_ignored()
    

  def when_asked_a_question(self):
    return 'Sure.'

  def when_yelled_at(self):
    # string is all caps
    return 'Woah, chill out!'

  def when_ignored(self):
    # don't say anything
    return 'Fine. Be that way!'

  def default_response(self):
    return 'Whatever.'
