class Bob

  def hey string
    case 
    when normal_tone?(string)
      'Fine. Be that way!'  
    when shouting?(string)
      'Woah, chill out!' 
    when question?(string)
      'Sure.'  
    else
      'Whatever.'
    end
  end

  private

  def normal_tone? string
    string.to_s.strip.empty?
  end

  def shouting? string
    string == string.upcase
  end

  def question? string
    string.end_with?('?')
  end

end
