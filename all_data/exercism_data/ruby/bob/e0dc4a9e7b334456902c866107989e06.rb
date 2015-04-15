class Message
  attr_reader :text
  def initialize(text)
    @text = text
  end
  def empty?
    text.strip.empty?
  end
  def shouting?
    lacks_lowercase? && has_uppercase?
  end
  def question?
    text.end_with? "?"
  end
  def lacks_lowercase?
    !text.match /[[:lower:]]/
  end
  def has_uppercase?
    text.match /[[:upper:]]/
  end
end

class Bob
  def hey(text)
    message = Message.new(text)
    return "Fine. Be that way!" if message.empty?
    return "Woah, chill out!" if message.shouting?
    return "Sure." if message.question?
    "Whatever."
  end
end
