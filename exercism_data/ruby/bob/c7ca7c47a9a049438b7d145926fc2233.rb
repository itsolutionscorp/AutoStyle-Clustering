class Bob
  def hey(message, responder = Responder.new)
    responder.respond_to(message)
  end
end

class Responder
  def responses_types
    [SilenceResponse, ShoutResponse, QuestionResponse, WhateverResponse]
  end

  def respond_to(message)
    responses_types.detect{ |r| r.answers?(message) }.response
  end
end

class Response
  def self.answers?(message)
    raise NotImplementedError
  end

  def self.response
    raise NotImplementedError
  end
end

class WhateverResponse < Response
  def self.answers?(message)
    true
  end

  def self.response
    'Whatever.'
  end
end

class QuestionResponse < Response
  def self.answers?(message)
    message.end_with? '?'
  end

  def self.response
    'Sure.'
  end
end

class SilenceResponse < Response
  def self.answers?(message)
    message.strip.empty?
  end

  def self.response
    'Fine. Be that way!'
  end
end


class ShoutResponse < Response
  def self.answers?(message)
    message == message.upcase
  end

  def self.response
    'Woah, chill out!'
  end
end
