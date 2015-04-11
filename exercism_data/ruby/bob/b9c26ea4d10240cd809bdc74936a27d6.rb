class Bob
  Response = {no_message: "Fine. Be that way!", shouting: "Woah, chill out!", question: "Sure."}
  def hey(message)
    @msg = Message.new(message)
    Response.fetch(message_type, "Whatever.")
  end

  def message_type
    return :no_message if message.not_given?
    return :shouting   if message.shouting?
    return :question   if message.questioning?
    return :statement
  end

  def message
    @msg
  end
end

class Message
  def initialize(message)
    @msg = String(message)
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

  def message
    @msg
  end
end
