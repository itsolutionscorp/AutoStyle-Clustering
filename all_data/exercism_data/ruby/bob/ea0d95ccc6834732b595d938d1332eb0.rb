class Bob
  
  def hey something
    if empty? something
      'Fine. Be that way!'
    elsif yell? something
      'Woah, chill out!'
    elsif question? something
      'Sure.'  
    else
      'Whatever.'
    end
  end

  def empty? something
    something == nil or something.strip == ""
  end
  
  def yell? something
    something == something.upcase
  end
  
  def question? something
    something.end_with? "?"
  end

end
