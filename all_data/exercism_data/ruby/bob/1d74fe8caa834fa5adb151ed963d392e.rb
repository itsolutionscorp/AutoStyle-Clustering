class Bob

  def hey(message)
    MessageTypeFactory.new(message).create.respond
  end

end

class MessageTypeFactory
  attr_reader :message

  def initialize(message)
    @message = message
  end

  def message_type
    message_types.find do |message_type|
      message_type.match? message
    end
  end

  def create
    message_type.new
  end

  def message_types
    [Silence, Shouting, Question, NullMessage]
  end

end

class MessageType
  def self.match?(message)
    matcher { message }
  end

  def respond
  end
end

class Silence < MessageType
  def self.matcher
    yield.strip == ""
  end

  def respond
    "Fine. Be that way!"
  end
end

class Shouting < MessageType
  def self.matcher
    message = yield
    message.upcase == message
  end

  def respond
    "Woah, chill out!"
  end
end

class Question < MessageType
  def self.matcher
    yield =~ /\?$/
  end

  def respond
    "Sure."
  end
end

class NullMessage < MessageType
  def self.matcher
    true
  end

  def respond
    "Whatever."
  end
end
