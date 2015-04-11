class Bob
  attr_reader :message
  RESPONSE = {no_message: "Fine. Be that way!", shouting: "Woah, chill out!", question: "Sure."}
  def hey(msg)
    @message = Message.new(msg)
    RESPONSE.fetch(message_type, "Whatever.")
  end

  def message_type
    return :no_message if message.not_given?
    return :shouting   if message.shouting?
    return :question   if message.questioning?
    return :statement
  end
end

class Message
  attr_reader :message
  def initialize(msg)
    @message = String(msg)
  end

  def shouting?
    message.upcase == message
  end

  def questioning?
    message.end_with?("?")
  end

  def not_given?
    message.empty?
  end
end
