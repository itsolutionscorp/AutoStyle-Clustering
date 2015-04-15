class Bob
  def hey(text)
    message = Message.new(text)

    if message.yelling?
      "Woah, chill out!"
    elsif message.question?
      "Sure."
    elsif message.nothing?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end

class Message
  def initialize(text)
    @text = text
  end

  def yelling?
    @text == @text.upcase and @text =~ /[A-Z]+/
  end

  def question?
    @text.end_with?('?')
  end

  def nothing?
    @text.strip.empty?
  end
end
