class Bob
  def hey(message)
    @message = message
    
    if silent
      response = 'Fine. Be that way!'
    elsif shouting_or_foreceful
      response = 'Woah, chill out!'
    elsif questioning_or_prattling
      response = 'Sure.'
    else
      response = 'Whatever.'
    end
  end

  def silent
    @message.to_s == ''
  end
  
  def shouting_or_foreceful 
    @message == @message.upcase
  end

  def questioning_or_prattling 
    @message.end_with?("?")
  end
end
