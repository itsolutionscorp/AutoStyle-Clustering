class Bob

  def hey(phrase)
    if is_shouting?(phrase)
      return "Woah, chill out!"
    elsif is_question?(phrase) 
      return "Sure."
    elsif is_silence?(phrase)
      return "Fine. Be that way!"
    else 
      return "Whatever."
    end
  end 

  def is_shouting?(phrase)
    phrase == phrase.upcase && has_some_characters?(phrase)
  end

  def has_some_characters?(phrase)
    phrase.count("A-Z") > 0
  end

  def is_question?(phrase)
    phrase[-1 , 1] == '?'
  end

  def is_silence?(phrase)
    (phrase.count("A-Z") == 0) && (phrase.count("0-9") == 0)    
  end

end
