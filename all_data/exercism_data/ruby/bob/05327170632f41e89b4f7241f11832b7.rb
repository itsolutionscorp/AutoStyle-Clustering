class Bob

  def hey(phrase)
    if ignoring?(phrase)
      'Fine. Be that way!'
    elsif shouting?(phrase) 
      'Woah, chill out!' 
    elsif asking?(phrase)
      'Sure.'   
    else
      'Whatever.'
    end
  end

  private 
  def shouting?(phrase)
    phrase == phrase.upcase
  end

  def asking?(phrase)
    phrase.end_with?("?")
  end

  def ignoring?(phrase)
    phrase.nil? or phrase.strip.empty?
  end

end
