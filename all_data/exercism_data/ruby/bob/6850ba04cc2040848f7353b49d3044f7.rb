class Bob
  def hey(message)
    message = Message.new(message)

    if message.silence?
      "Fine. Be that way!"
    elsif message.shout?
      "Woah, chill out!"
    elsif message.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Message
  attr_accessor :message

  def initialize(message)
    self.message = message
  end

  def shout?
    message == message.upcase
  end

  def question?
    message.end_with?("?")
  end

  def silence?
    message.strip.size == 0
  end
end
