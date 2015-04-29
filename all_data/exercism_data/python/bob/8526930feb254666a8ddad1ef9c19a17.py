class Bob:
  def hey(self, message):
    def shouting(message):
      return any(ch.isalpha() for ch in message) and (message == message.upper())
    def question(message):
      return message[-1] == '?'
    def silence(message):
      return message.strip() == ''

    if silence(message):
      return 'Fine. Be that way!'
    elif shouting(message):
      return 'Woah, chill out!'
    elif question(message):
      return 'Sure.'
    else:
      return 'Whatever.'
