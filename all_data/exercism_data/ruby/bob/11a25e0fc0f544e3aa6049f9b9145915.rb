class Bob
  attr_reader :message
  def hey(msg)
    @message = String(msg)
    respond
  end
  
  private
  def question?
    message.end_with? '?'
  end

  def ignored?
    message.empty?
  end

  def upset?
    message.upcase == message
  end

  def respond
    if ignored?
      "Fine. Be that way."
    elsif upset?
      "Woah, chill out!" 
    elsif question?
      "Sure."
    else
      "Whatever."
    end
  end

end
