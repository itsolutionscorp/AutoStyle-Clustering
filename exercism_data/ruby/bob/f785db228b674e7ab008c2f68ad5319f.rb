class Bob
  def hey(message_string)
    message = Message.new message_string

    case
    when message.empty?
      "Fine. Be that way!"
    when message.shout?
      "Woah, chill out!"
    when message.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Message

  attr_accessor :message

  def initialize(message)
    @message = message
  end

  def empty?
    @message.strip.empty?
  end

  def shout?
    @message =~ /[A-Z]/ && @message.upcase == @message
  end

  def question?
    @message =~ /\?\z/
  end
end
