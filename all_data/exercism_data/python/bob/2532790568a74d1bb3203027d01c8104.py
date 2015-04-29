''' Simple module for talking to Bob
'''

import conversation

class LackadaisicalTeenager(object):
  # stub class
  pass 

class Bob(LackadaisicalTeenager):
  ''' Bob is a lackadaisical teenager. He is able to respond to simple statements
  in a limited manner via the 'hey' method.
  '''

  _questionResponse = "Sure."
  _yellResponse     = "Woah, chill out!"
  _silenceResponse  = "Fine. Be that way!"
  _defaultResponse  = "Whatever."

  def __init__(self):
    super(Bob, self).__init__()

    question = conversation.Question(Bob._questionResponse)
    yell     = conversation.Yell(Bob._yellResponse)
    silence  = conversation.AwkwardSilence(Bob._silenceResponse)

    self._conversation = conversation.Conversation(Bob._defaultResponse, question, yell, silence)

  def hey(self, statementStr):
    ''' Get Bobs response to the specified statement.
    '''
    return self._conversation.respond(statementStr)
