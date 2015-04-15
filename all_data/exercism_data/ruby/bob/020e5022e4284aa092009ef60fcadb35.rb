module ShoutResponse
  def self.hears?(message)
    message.upcase == message
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
    message.empty?
  end

  def self.reply
    'Fine. Be that way!'
  end
end

class Bob
  RESPONDERS = [QuietResponse, ShoutResponse, StatementResponse, QuestionResponse]

  def hey(message)
    first_responder_that_hears(message).reply
  end

  private

  def first_responder_that_hears(message)
    RESPONDERS.find { |responder| responder.hears?(message.to_s) }
  end
end
