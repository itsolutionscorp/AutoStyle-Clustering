class Bob
  def hey text
    message = Message.new text
    lackadaisical_reponse message
  end

  def lackadaisical_reponse message
    return "Fine. Be that way!" if message.silence?
    return "Woah, chill out!" if message.shouting?
    return "Sure." if message.question?
    "Whatever."
  end
end

class Message
  attr_reader :text
  def initialize text
    @text = text || ""
  end

  def silence?
    text.empty?
  end

  def shouting?
    text.upcase == text
  end

  def question?
    text[-1] == ??
  end
end
