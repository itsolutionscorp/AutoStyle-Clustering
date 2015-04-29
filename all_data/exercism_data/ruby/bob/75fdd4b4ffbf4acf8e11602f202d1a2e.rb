class Bob

  def hey text
    responder = ResponderFactory.get_responder "Bob"
    message = Message.new(text)

    responder.answer message
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

  def answer message
    return "Fine. Be that way!" if message.silent?
    return "Woah, chill out!" if message.shouting?
    return "Sure." if message.asking?

    "Whatever."
  end

end

class ResponderFactory

  def self.get_responder name
    case name
      when "Bob"
        BobResponder.new
    end
  end

end
