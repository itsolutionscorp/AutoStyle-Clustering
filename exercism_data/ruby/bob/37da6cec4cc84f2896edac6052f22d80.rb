class MessageType
  attr_reader :message

  def initialize(message)
    @message = message
  end
end

class SilenceQuestion < MessageType
  def self.match?(message)
    message.strip.empty?
  end

  def reply
    'Fine. Be that way!'
  end
end

class YellMessage < MessageType
  def self.match?(message)
    message == message.upcase
  end

  def reply
    'Woah, chill out!'
  end
end

class QuestionMessage < MessageType
  def self.match?(message)
    message.end_with?('?')
  end

  def reply
    'Sure.'
  end
end

class UnkownMessage < MessageType
  def self.match?(_)
    true
  end

  def reply
    'Whatever.'
  end
end

module MessageFactory
  MessageTypes = [SilenceQuestion, YellMessage, QuestionMessage, UnkownMessage]

  def self.get(message)
    klass = MessageTypes.detect { |klass| klass.match?(message) }
    klass.new(message)
  end
end

class Bob
  def hey(message)
    MessageFactory.get(message).reply
  end
end
