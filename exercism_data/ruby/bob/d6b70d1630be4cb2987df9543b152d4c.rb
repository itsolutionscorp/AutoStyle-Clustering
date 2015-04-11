class Bob

  def hey message_value
    message = Message.new(message_value)
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

  def initialize value
    @value = value.to_s
  end

  def nothing?
    @value.empty?
  end

  def yell?
    @value.upcase == @value
  end

  def question?
    @value.end_with?("?")
  end

end
