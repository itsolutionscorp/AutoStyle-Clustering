class Bob
  def hey(message)
    message = Message.new(message)

    if message.nothing?
      "Fine. Be that way!"
    elsif message.yell?
      "Woah, chill out!"
    elsif message.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Message
  attr_reader :message

  def initialize(message)
    @message = message
  end

  def nothing?
    message.strip.empty?
  end

  def question?
    message.end_with?("?")
  end

  def yell?
    message == message.upcase
  end
end
