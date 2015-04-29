class Bob

  def hey(sound)
    message = Message.new sound

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
    @content =~ /\?$/
  end

  def silence?
    @content =~ /^\s*$/
  end

end
