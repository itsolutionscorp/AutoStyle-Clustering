class MessageAnalyzer

  attr_accessor :text

  def initialize(text)
    @text = text
  end

  def empty?
    text.strip.empty?
  end

  def all_caps?
    text == text.upcase
  end

  def asking?
    text.end_with?("?")
  end
end

class Bob
  def hey(text)
    message = MessageAnalyzer.new(text)

    return "Fine. Be that way!" if message.empty?
    return "Woah, chill out!" if message.all_caps?
    return "Sure." if message.asking?

    "Whatever."
  end
end
