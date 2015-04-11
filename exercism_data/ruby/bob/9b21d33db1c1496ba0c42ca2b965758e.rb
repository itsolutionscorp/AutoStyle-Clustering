class Message
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def silence?
    text.strip.empty?
  end

  def shouting?
    text == text.upcase
  end

  def asking?
    text.end_with?("?")
  end
end

class Bob
  def hey(text)
    message = Message.new(text)

    return "Fine. Be that way!" if message.silence?
    return "Woah, chill out!" if message.shouting?
    return "Sure." if message.asking?

    "Whatever."
  end
end
