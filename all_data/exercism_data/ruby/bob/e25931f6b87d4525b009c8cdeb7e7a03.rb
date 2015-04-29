class Message
  def initialize(message)
    @message = message
  end

  attr_reader :message

  def yelling?
    message.upcase == message
  end

  def question?
    message.end_with?("?")
  end

  def none?
    # where is active support when you need it! blank?
    message.nil? || message == ""
  end
end

class Bob
  def hey(words)
    message = Message.new(words)

    if message.none?
      "Fine. Be that way!"
    elsif message.yelling?
      "Woah, chill out!"
    elsif message.question?
      "Sure."
    else
      "Whatever."
    end
  end
end
