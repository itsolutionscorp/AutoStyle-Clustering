class Bob
  def hey(message = "")
    message = Message.new(message)

    if message.blank?
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

class Message
  attr_reader :message

  def initialize(message = "")
    @message = message
  end

  def blank?
    # message contains no non-whitespace or is simply empty
    message !~ /\S/ || message.empty?
  end

  def yelling?
    # message contains alpha characters and is all uppercase
    message =~ /[a-z]/i && message.upcase == message
  end

  def question?
    # message has no newline characters and ends with a question mark
    message !~ /\n/ && message[-1, 1] == "?"
  end
end
