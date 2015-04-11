class Bob

  def hey(message)
    Teenager.new(message).interpret
  end

end

class Teenager
  attr_reader :message_string

  def initialize(message_string)
    @message_string = message_string.to_s
  end

  def interpret
    self.send understood_messages.map(&:new).find { |msg| msg.matches?(message_string) }.respond
  end

  def default
    "Whatever."
  end

  def shouting
    'Woah, chill out!'
  end

  def question
     'Sure.'
  end

  def silent
    'Fine. Be that way!'
  end

private
  def understood_messages
    [SilentMessage, ShoutingMessage, QuestionMessage, DefaultMessage]
  end

end

class DefaultMessage
  def matches?(_); true; end
  def respond; :default; end
end

class ShoutingMessage
  def matches?(message); message !~ /[a-z]/; end
  def respond; :shouting; end
end

class QuestionMessage
  def matches?(message); message.end_with?("?"); end
  def respond; :question; end
end

class SilentMessage
  def matches?(message); message.empty?; end
  def respond; :silent; end
end
