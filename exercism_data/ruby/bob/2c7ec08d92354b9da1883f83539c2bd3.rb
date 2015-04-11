class Bob

  def hey(phrase)
    return 'Fine. Be that way!' if they_are_ignoring_me(phrase)
    return 'Woah, chill out!' if they_are_shouting_a(phrase) 
    return 'Sure.' if it_sounds_like_a_question(phrase)  
    return 'Whatever.'
  end

  private 
  def they_are_shouting_a(phrase)
    phrase == phrase.upcase
  end

  def it_sounds_like_a_question(phrase)
    phrase.end_with?("?")
  end

  def they_are_ignoring_me(phrase)
    phrase.nil? || phrase.strip.empty?
  end

end
