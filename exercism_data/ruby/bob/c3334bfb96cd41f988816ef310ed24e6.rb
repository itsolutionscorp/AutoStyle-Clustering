module ShoutResponder
  def self.hears?(message)
    message.upcase == message
  end

  def self.reply
    'Woah, chill out!'
  end
end

module StatementResponder
  def self.hears?(message)
    true
  end

  def self.reply
    'Whatever.'
  end
end

module QuestionResponder
  def self.hears?(message)
    message.end_with?('?')
  end

  def self.reply
    'Sure.'
  end
end

module QuietResponder
  def self.hears?(message)
    message.empty?
  end

  def self.reply
    'Fine. Be that way!'
  end
end

class Bob
  RESPONDERS = [QuietResponder, ShoutResponder, QuestionResponder, StatementResponder]

  def hey(message)
    first_responder_that_hears(message).reply
  end

  private

  def first_responder_that_hears(message)
    RESPONDERS.find { |responder| responder.hears?(message.to_s) }
  end
end
