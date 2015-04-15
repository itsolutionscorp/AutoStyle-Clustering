class Bob
  @message
  def hey(message)    
    @message = message
    if silence?
      'Fine. Be that way!'
    elsif shout?
      'Woah, chill out!'
    elsif question?
      'Sure.'      
    else
      'Whatever.'
    end  
  end
  
  private
  def silence?
    @message =~ /\A\s*\Z/
  end
  
  def shout?
    @message.upcase == @message && @message.downcase != @message
  end
  
  def question?
    @message[-1] == '?'
  end
  
end
