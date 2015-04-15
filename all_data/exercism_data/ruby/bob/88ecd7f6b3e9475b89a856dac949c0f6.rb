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

  attr_reader :value

  def initialize value
    @value = value
  end

  def nothing?
    value.nil? || value.empty?
  end

  def yell?
    !value.nil? && value.upcase == value
  end

  def question?
    !value.nil? && value.end_with?("?")
  end

end
