class Bob
  
  def hey(input)
    if silence?(input)
      'Fine. Be that way!'
    elsif yelling?(input) 
      'Woah, chill out!'
    elsif question?(input)
      'Sure.'
    else
      'Whatever.'
    end
  end
  
  private
  
  def silence?(input)
    input.strip.empty?
  end

  def yelling?(input)
    input.upcase == input && input =~ /[A-Z]/
  end

  def question?(input)
    input.end_with? '?'
  end

end
