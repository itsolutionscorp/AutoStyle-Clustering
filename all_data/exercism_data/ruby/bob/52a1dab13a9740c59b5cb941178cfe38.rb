class NothingResponse
  def self.accept_message?(message)
    message.empty?
  end

  def self.answer
    'Fine. Be that way.'
  end
end

class YellingResponse
  def self.accept_message?(message)
    message.upcase == message
  end

  def self.answer
    "Woah, chill out!"
  end
end

class DefaultResponse

  def self.accept_message?(message)
    true
  end

  def self.answer
    "Whatever."
  end
end

class QuestionResponse
  def self.accept_message?(message)
    message[-1] == '?'
  end

  def self.answer
    "Sure."
  end
end

module LackadaisicalTeenager

  RESPONSES = [NothingResponse, YellingResponse, QuestionResponse, DefaultResponse]

  def respond(message)

    response = RESPONSES.find { | response | response.accept_message?(message) }
    response.answer

  end

end

class Bob
  include LackadaisicalTeenager

  def hey(message)
    respond(message)
  end
end
