class Bob

  def hey(message)
    message = Message.new message

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
    @content = content
  end

  def shouted?
    !silence? && @content.upcase == @content
  end

  def question?
    !silence? && @content =~ /\?$/
  end

  def silence?
    @content == nil || @content =~ /^\s*$/
  end

end
