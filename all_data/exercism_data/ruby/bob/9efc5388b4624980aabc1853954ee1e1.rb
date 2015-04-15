class Bob
  def hey(phrase)
    case
      when silence?(phrase) then 'Fine. Be that way!'
      when shouting?(phrase) then 'Woah, chill out!'
      when question?(phrase) then 'Sure.'
      else 'Whatever.'
    end
  end

  private

  def question?(phrase)
    phrase.end_with?('?')
  end

  def shouting?(phrase)
    phrase.upcase == phrase
  end

  def silence?(phrase)
    phrase.to_s.strip.empty?
  end
end
