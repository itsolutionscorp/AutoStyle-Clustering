class Bob
  
  def hey(phrase)
    case 
    when nothing_is_said?(phrase) 
      'Fine. Be that way!'
    when yelled_at?(phrase) 
      'Woah, chill out!'
    when asked_a_question?(phrase) 
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def nothing_is_said?(phrase)
    phrase.to_s.strip.empty?
  end

  def yelled_at?(phrase)
    !phrase.match(/\p{Lower}/)
  end

  def asked_a_question?(phrase)
    phrase.end_with?('?')
  end
end
