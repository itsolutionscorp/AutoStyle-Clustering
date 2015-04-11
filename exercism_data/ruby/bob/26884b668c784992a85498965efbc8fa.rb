class Bob
  attr_accessor :message

  def hey(message)
    self.message = message
    return "Sure." if question?
    return "Woah, chill out!" if yelled?
    return "Fine. Be that way!" unless said_something?
    "Whatever."
  end

  def question?
    !yelled? && message[-1] == "?"
  end

  def yelled?
    said_something? && message.upcase == message
  end

  def said_something?
    !message.strip.empty?
  end
end
