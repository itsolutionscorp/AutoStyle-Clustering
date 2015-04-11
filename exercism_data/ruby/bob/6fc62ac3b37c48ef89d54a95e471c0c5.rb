class Bob

  def hey string
    if empty(string)
     'Fine. Be that way!'  
    elsif shouting(string)
     'Woah, chill out!' 
    elsif question(string)
     'Sure.'  
    else
     'Whatever.'
    end
  end

  private

  def empty string
    string.to_s.strip.empty?
  end

  def shouting string
    string == string.upcase
  end

  def question string
    string.end_with?('?')
  end

end
