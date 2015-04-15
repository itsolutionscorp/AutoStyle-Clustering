class Bob
  attr_reader :message

  def hey(message)
    @message = message
  
    if silent?
      'Fine. Be that way!'
    elsif shouting?
      'Woah, chill out!' 
    elsif question?
      "Sure."
    else
      "Whatever."
    end
  end

  def shouting?
    upcased? && !(message =~ /^(\d+,?\s?)+\??$/)
  end

  def question?
    message[-1] == "?" 
  end

  def upcased?
    message == message.upcase  
  end

  def silent?
    message.strip.empty? 
  end
end
