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

class PhraseAnalyser < Struct.new(:phrase)
  def silence?
    phrase.nil? || phrase.strip.empty?
  end

  def shouting?
    phrase == phrase.upcase
  end

  def question?
    phrase.end_with? '?'
  end
end
