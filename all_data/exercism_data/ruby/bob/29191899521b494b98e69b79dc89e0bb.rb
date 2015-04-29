class Bob
  attr_accessor :message

  def hey(message)
    @message = message
    respond
  end

  private
  
  def respond
    case
    when yelling?(@message)
      "Woah, chill out!"
    when question?(@message)
      "Sure."
    when silent?(@message)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  def yelling?(msg)
    msg.upcase == msg and msg.match(/[A-Z]/)
  end

  def question?(msg)
    msg.end_with?("?")
  end

  def silent?(msg)
    msg.strip.empty?
  end
end
