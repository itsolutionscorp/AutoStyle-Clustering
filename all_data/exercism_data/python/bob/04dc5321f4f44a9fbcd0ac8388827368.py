class Bob():
  def hey(self, message):
    message = Bob.Message(message)

    if message.isSilence():
      return 'Fine. Be that way!'
    elif message.isShout():
      return 'Woah, chill out!'
    elif message.isQuestion():
      return 'Sure.'
    else:
      return 'Whatever.'

  class Message():
    def __init__(self, message):
      self.message = message

    def isSilence(self):
      return self.message is None or self.message.strip() == ""

    def isShout(self):
      return self.message == self.message.upper()

    def isQuestion(self):
      return self.message[-1] == "?"
