class Bob
  def hey message
    m = Message.new(message)

    if m.silent?
      "Fine. Be that way!"
    elsif m.loud?
      "Woah, chill out!"  
    elsif m.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Message
  def initialize message
    @message = message
  end

  def silent?
    @message.strip.empty?
  end

  def loud?
    @message.upcase == @message
  end

  def question?
    @message.end_with?("?")
  end
end
