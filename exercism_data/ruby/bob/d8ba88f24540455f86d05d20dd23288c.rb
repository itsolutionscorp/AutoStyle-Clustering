class Bob

  def hey text
    message = Message.new(text)
    BobResponder.new(message).answer
  end

end

class Message

  def initialize text
    @text = text
  end

  def silent?
    @text.strip == ""
  end

  def shouting?
    @text.upcase == @text
  end

  def asking?
    @text.end_with? "?"
  end

end

class BobResponder

  def initialize message
    @message = message
  end

  def answer
    return "Fine. Be that way!" if @message.silent?
    return "Woah, chill out!" if @message.shouting?
    return "Sure." if @message.asking?

    "Whatever."
  end

end
