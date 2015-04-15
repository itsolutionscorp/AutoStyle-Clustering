class Bob
  def hey message
    message.strip!
    
    if message.empty?
      'Fine. Be that way!'
    elsif yelled_at? message
      'Woah, chill out!'
    elsif asked? message
      'Sure.'
    else
      'Whatever.'
    end
  end
  
  private
  def yelled_at? message
    !!(message =~ /\A(?=[^a-z]*[A-Z])[^a-z]+\z/)
  end
  
  def asked? message
    message.end_with? '?'
  end
end
