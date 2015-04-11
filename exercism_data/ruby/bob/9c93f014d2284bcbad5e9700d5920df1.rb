class Bob

  def hey text
    message = BobMessage.new(text)
    message.answer
  end

end

class BobMessage

  def initialize text
    @text = text
  end

  def answer
    return "Fine. Be that way!" if silent?
    return "Woah, chill out!" if shouting?
    return "Sure." if asking?

    "Whatever."
  end

  private

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
