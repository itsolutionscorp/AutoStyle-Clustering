class Message
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def angry?
    text.upcase == text && text.gsub(/[a-zA-Z]/, '') != text
  end

  def inquisitive?
    text.end_with? '?'
  end

  def taciturn?
    text.strip.empty?
  end
end

class Bob
  def hey(text)
    message = Message.new(text)

    if message.angry?
      "Woah, chill out!"
    elsif message.inquisitive?
      "Sure."
    elsif message.taciturn?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
