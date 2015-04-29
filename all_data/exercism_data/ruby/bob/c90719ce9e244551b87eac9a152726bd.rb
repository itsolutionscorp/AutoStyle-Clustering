class Bob

  def hey(string)
    message = Message.new(string)
    return "Fine. Be that way." if message.missing?
    return 'Woah, chill out!' if message.shouting?(string)
    return "Sure." if message.is_a_question?
    return "Whatever." 
  end

end


class Message

  def initialize(string)
    @message = string
  end

  def shouting?(string)
    @message.upcase == string
  end

  def missing?
    @message == "" || @message.nil?
  end

  def is_a_question?
    @message.end_with? "?" 
  end

end
