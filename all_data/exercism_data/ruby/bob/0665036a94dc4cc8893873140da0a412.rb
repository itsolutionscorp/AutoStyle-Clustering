module ShoutResponse
  def self.hears?(message)
    message.length > 0 && message.upcase == message
  end

  def self.reply
    'Woah, chill out!'
  end
end

module StatementResponse
  def self.hears?(message)
    message =~ /[\.!]$/
  end

  def self.reply
    'Whatever.'
  end
end

module QuestionResponse
  def self.hears?(message)
    message =~ /\?$/
  end

  def self.reply
    'Sure.'
  end
end

module QuietResponse
  def self.hears?(message)
    message.length == 0
  end

  def self.reply
    'Fine. Be that way!'
  end
end

class Bob
  RESPONDERS = [ShoutResponse, StatementResponse, QuestionResponse, QuietResponse]

  def hey(message)
    RESPONDERS.find { |responder| responder.hears?(message.to_s) }.reply
  end
end
