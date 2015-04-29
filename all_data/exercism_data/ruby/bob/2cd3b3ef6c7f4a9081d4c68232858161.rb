class Bob
  def hey (msg)
    @message = msg
    respond
  end
  def asking?
    @message.end_with? '?'
  end
  def ignoring?
    @message.nil? || @message.empty?
  end
  def shouting?
    @message.upcase == @message
  end

  def respond
    if ignoring?
      return "Fine. Be that way."
    elsif shouting?
      return "Woah, chill out!" 
    elsif asking?
      return "Sure."
    else
      "Whatever."
    end
  end
end
