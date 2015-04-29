class Bob
  def hey(nonsense)
    if silence(nonsense)
      'Fine. Be that way!'
    elsif yell(nonsense)
      'Woah, chill out!'
    elsif question(nonsense)
      'Sure.'
    else
      'Whatever.'
    end
  end
  
  private

  def silence(string)
    string.strip.empty?
  end
  
  def yell(string)
    string == string.upcase
  end
    
  def question(string)
    string.end_with?("?")
  end
end
