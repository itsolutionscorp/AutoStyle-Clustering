class Bob

  def hey(words)
    case 
    when yelling?(words)
      'Woah, chill out!'
    when question?(words)
      'Sure.'
    when quiet?(words)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  def yelling?(words)
    words.match(/[a-zA-Z]/) && words.upcase.eql?(words) 
  end

  def question?(words)
    words.end_with?('?') 
  end

  def quiet?(words)
    words.strip.empty?
  end

end
