class Bob

  def hey(message_string)
    message = Message.new(message_string)
    TeenagerResponse.new.response(message)
  end

end

class Message

  def initialize(message)
    @message = message || ""
  end

  def message_type
    return :silent if silent?
    return :yelling if yelling?
    return :question if question?
    :default
  end

  def question?
    @message.end_with? "?"
  end  

  def yelling?
    @message.upcase == @message
  end

  def silent?
    @message.strip == ""
  end

end

class TeenagerResponse

  def responses
    {
      silent: "Fine. Be that way!",
      yelling: "Woah, chill out!",
      question: "Sure."
      # default: "Whatever."
    }
  end

  def response(message)
    responses[message.message_type] || "Whatever."  
  end

end
