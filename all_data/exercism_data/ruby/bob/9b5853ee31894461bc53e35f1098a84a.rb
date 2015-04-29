class Bob

  def hey(phrase)
    if they_are_ignoring_me(phrase)
      'Fine. Be that way!'
    elsif they_are_shouting_a(phrase) 
      'Woah, chill out!' 
    elsif it_sounds_like_a_question(phrase)
      'Sure.'   
    else
     'Whatever.'
    end
  end

  private 
  def they_are_shouting_a(phrase)
    phrase == phrase.upcase
  end

  def it_sounds_like_a_question(phrase)
    phrase.end_with?("?")
  end

  def they_are_ignoring_me(phrase)
    phrase.nil? or phrase.strip.empty?
  end

end
