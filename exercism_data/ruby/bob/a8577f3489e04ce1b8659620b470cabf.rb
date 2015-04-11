class Bob
  # Respond to a message.
  #
  # text - A string message.
  #
  # Returns a string response.
  def hey(text)
    message = Message.new(text)
    reply_to(message)
  end

  protected

  def reply_to(message)
    case
    when message.silent?
      "Fine. Be that way!"
    when message.yelling?
      "Woah, chill out!"
    when message.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Message
  attr_reader :text
  def initialize(text)
    @text = text
  end

  def question?
    text =~ /\?\z/
  end

  def silent?
    text =~ /\A\s*\z/
  end

  def yelling?
    text =~ /\A[A-Z\d\W]+\z/
  end
end
