class Bob

  def hey(phrase)
    return 'Fine. Be that way!' if they_are_ignoring_me(phrase)
    return 'Woah, chill out!' if they_are_shouting_a(phrase) 
    return 'Sure.' if it_sounds_like_a_question(phrase)  
    return 'Whatever.'
  end

  private 
  def they_are_shouting_a(phrase)
    phrase.to_s == phrase.upcase.to_s
  end

  def it_sounds_like_a_question(phrase)
    phrase[-1] == "?"
  end

  def they_are_ignoring_me(phrase)
    phrase.nil? || phrase.strip.empty?
  end

end
