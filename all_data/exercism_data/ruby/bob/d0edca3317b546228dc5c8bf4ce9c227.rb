class Bob

  def hey(message)
    msg = Message.new(message)
    if msg.silent?
      "Fine. Be that way!"
    elsif msg.yelling?
      "Woah, chill out!"
    elsif msg.question?
      "Sure."
    else
      "Whatever."
    end
  end

end

class Message

  def initialize(message)
    @message = message || ""
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
