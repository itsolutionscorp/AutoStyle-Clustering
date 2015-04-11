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
    @msg = message
  end

  def shouting?
    message.delete('^[a-zA-Z]').match(/^[A-Z]+$/)
  end

  def questioning?
    message[-1] == "?"
  end

  def not_given?
    message.respond_to?(:empty?) ? message.empty? : !message
  end

  def message
    @msg
  end
end
