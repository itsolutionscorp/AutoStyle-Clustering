''' Simple module for talking to Bob
'''

# TODO move the statements and Conversation into a separate module

class AbstractStatement(object):
  ''' Statement base class.
  Subclasses must define an asked method. This is used to determine
  whether the supplied string matches the kind of statement represented
  by the class.
  '''
  def __init__(self, response):
    self._response = response

  def response(self):
    return self._response

class Question(AbstractStatement):
  ''' Statement representing someone asking a question.
  '''
  def asked(self, statementStr):
    ''' Return whether statementStr is a question or not.
    '''
    question = statementStr.endswith("?") and not statementStr.isupper()
    return question

class Yell(AbstractStatement):
  ''' Statement representing someone yelling.
  '''
  def asked(self, statementStr):
    ''' Return whether statementStr is being yelled or not.
    '''
    return statementStr.isupper()

class AwkwardSilence(AbstractStatement):
  ''' Statement representing someone being silent.
  '''
  def asked(self, statementStr):
    ''' Return whether statementStr is silence.
    '''
    silence = len(statementStr) == 0 or statementStr.isspace()
    return silence

class Conversation(object):
  ''' Class representing a conversation based on a series of statements.
  If none of those statements match what is asked then a default response is
  given.
  '''
  def __init__(self, defaultResponse, *supportedStatements):
    ''' Create a new conversation which supports the given statements,
    and otherwise replies with defaultResponse.
    '''
    self._defaultResponse = defaultResponse
    self._supportedStatements = supportedStatements

  def respond(self, statementStr):
    ''' Respond to the given statement.
    '''
    for supportedStatement in self._supportedStatements:
      if supportedStatement.asked(statementStr):
        return supportedStatement.response()

    return self._defaultResponse

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

    question = Question(Bob._questionResponse)
    yell     = Yell(Bob._yellResponse)
    silence  = AwkwardSilence(Bob._silenceResponse)

    self._conversation = Conversation(Bob._defaultResponse, question, yell, silence)

  def hey(self, statementStr):
    ''' Get Bobs response to the specified statement.
    '''
    return self._conversation.respond(statementStr)
