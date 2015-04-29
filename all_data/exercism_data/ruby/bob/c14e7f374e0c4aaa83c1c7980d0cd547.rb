class Bob
  def hey(message)
    return "Fine. Be that way." if silent? message
    return "Woah, chill out!" if all_caps? message
    return "Sure." if question? message
    "Whatever."
  end

  private

  def silent?(message)
    message.nil? || message.empty?
  end

  def all_caps?(message)
    message == message.upcase
  end

  def question?(message)
    return false if message.empty?

    last_char(message) == '?'
  end

  def exclamation?(message)
    return false if message.empty?

    last_char(message) == '!'
  end

  def last_char(message)
    message[message.length-1]
  end
end
