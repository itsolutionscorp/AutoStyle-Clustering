class Bob

  def hey(sentence)
    message = Message.new sentence

    if message.shouted?
      "Woah, chill out!"
    elsif message.question?
      "Sure."
    elsif message.silence?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end

class Message
  def initialize(content)
    @content = content || ""
  end

  def shouted?
    !silence? && @content.upcase == @content
  end

  def question?
    @content.end_with? "?"
  end

  def silence?
    @content.strip.empty?
  end
end
