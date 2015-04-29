class Bob
  def hey(text)
    message = Message.new(text)

    return "Fine. Be that way." if message.silent?
    return "Woah, chill out!" if message.all_caps?
    return "Sure." if message.question?
    "Whatever."
  end
end

class Message
  def initialize(message)
    @message = String(message)
  end

  def silent?
    @message.empty?
  end

  def all_caps?
    @message == @message.upcase
  end

  def question?
    @message.end_with? '?'
  end
end
