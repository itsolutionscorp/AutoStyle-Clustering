class Bob

  def hey(message)
    if shouting?(message)
      'Woah, chill out!'
    elsif question?(message) 
      'Sure.'
    elsif silence?(message)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
  
  def shouting?(message)
    word_chars = message.chars.select { |c| c =~ /[[:alpha:]]/ }
    word_chars.length > 0 && word_chars.all? { |c| c =~ /[A-Z]/ }
  end
  
  def question?(message)
    message.end_with? '?'
  end
  
  def silence?(message)
    message.strip.empty?
  end

end
