class MessageType
  attr_reader :message

  def initialize(message)
    @message = message
  end
end

class SilenceQuestion < MessageType
  def match?
    message.strip.empty?
  end

  def reply
    'Fine. Be that way!'
  end
end

class YellMessage < MessageType
  def match?
    message == message.upcase
  end

  def reply
    'Woah, chill out!'
  end
end

class QuestionMessage < MessageType
  def match?
    message.end_with?('?')
  end

  def reply
    'Sure.'
  end
end

class UnkownMessage < MessageType
  def match?
    true
  end

  def reply
    'Whatever.'
  end
end

# I would like to try this excersie without IF or Proc without intention revealing names
class Bob
  MessageTypes = [SilenceQuestion, YellMessage, QuestionMessage, UnkownMessage]

  def hey(message)
    MessageTypes.each do |klass|
      m = klass.new(message)
      return m.reply if m.match?
    end
  end
end
