class Bob
  def hey(phrase)
    case
      when is_silence?(phrase) then 'Fine. Be that way!'
      when is_shouting?(phrase) then 'Woah, chill out!'
      when is_a_question?(phrase) then 'Sure.'
      else 'Whatever.'
    end
  end

  private

  def is_a_question?(phrase)
    phrase.end_with?('?')
  end

  def is_shouting?(phrase)
    phrase.upcase == phrase
  end

  def is_silence?(phrase)
    phrase.to_s.strip.empty?
  end
end
