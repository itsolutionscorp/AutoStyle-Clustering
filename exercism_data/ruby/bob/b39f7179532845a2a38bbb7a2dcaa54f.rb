class Bob
  attr_reader :saying
  
  def hey(phrase)
    case 
    when asked_a_question?(phrase) 
      'Fine. Be that way!'
    when told_something?(phrase) 
      'Woah, chill out!'
    when yelled_at?(phrase) 
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def asked_a_question?(phrase)
    phrase.to_s.strip.length == 0
  end

  def told_something?(phrase)
    !phrase.match(/\p{Lower}/)
  end

  def yelled_at?(phrase)
    phrase[-1, 1] == '?'
  end
end
