class Bob
  def hey(message)
    MessageFactory.create_for(message).reply
  end
end

class NoMessage
  def self.matches?(message)
    message.strip.empty?
  end

  def self.reply
    'Fine. Be that way!'
  end
end

class ShoutingMessage
  def self.matches?(message)
    message.upcase == message
  end

  def self.reply
    'Woah, chill out!'
  end
end

class QuestionMessage
  def self.matches?(message)
    message.strip.end_with?('?')
  end

  def self.reply
    'Sure.'
  end
end

class UnknownMessage
  def self.matches?(message)
    true
  end

  def self.reply
    'Whatever.'
  end
end

class MessageFactory
  KNOWN_MESSAGES = [NoMessage, ShoutingMessage, QuestionMessage, UnknownMessage]

  def self.create_for(message)
    KNOWN_MESSAGES.find do |message_type|
      message_type if message_type.matches?(message)
    end
  end
end
