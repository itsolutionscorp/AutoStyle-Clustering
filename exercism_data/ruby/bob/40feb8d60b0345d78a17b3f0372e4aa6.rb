class Bob

  def hey(phrase)

    phrase_analyser = PhraseAnalyser.new(phrase)

    if phrase_analyser.silence?
      'Fine. Be that way!'
    elsif phrase_analyser.shouting?
      'Woah, chill out!'
    elsif phrase_analyser.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

end


PhraseAnalyser = Struct.new(:phrase) do
  def silence?
    phrase.nil? || phrase.strip.empty?
  end

  def shouting?
    ! phrase.nil? && phrase == phrase.upcase
  end

  def question?
    ! phrase.nil? && phrase.end_with? '?'
  end
end
