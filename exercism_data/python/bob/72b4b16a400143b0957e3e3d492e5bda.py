class Bob(object):

  def hey(self, sentence):
    if (self.silence(sentence)):
      return 'Fine. Be that way!'
    if(self.shouting(sentence)):
      return 'Woah, chill out!'
    if(self.question(sentence)):
      return 'Sure.'
    return 'Whatever.'

  def silence(self, sentence):
    return len(sentence.strip()) == 0 or sentence.isspace()

  def question(self, sentence):
    return sentence.endswith('?')

  def shouting(self, sentence):
    return sentence.isupper()
