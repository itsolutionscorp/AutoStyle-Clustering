class Bob
  def hey(incomming_message)
    message = Message.new(incomming_message)
    if message.shouting?
      "Woah, chill out!"
    elsif message.question?
      "Sure."
    elsif message.silence?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end

class Message
  def initialize(message)
    @message = message
  end

  def question?
    @message.match /\A.*\?\z/
  end

  def silence?
    @message.strip.empty?
  end

  def shouting?
    @message == @message.upcase && @message =~ /[A-Z]/
  end
end
