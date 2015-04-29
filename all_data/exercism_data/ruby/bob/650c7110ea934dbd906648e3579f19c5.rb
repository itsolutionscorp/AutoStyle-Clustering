class Bob

  def hey(phrase)

    analyzer = PhraseAnalyzer.new(phrase)

    if analyzer.silence?
      'Fine. Be that way!'
    elsif analyzer.shouting?
       'Woah, chill out!'
    elsif analyzer.question?
       'Sure.'
    else 
       'Whatever.'
    end

  end
    
end


class PhraseAnalyzer

  def initialize(phrase)
    @phrase = phrase
  end

  def silence?
    @phrase.to_s.strip.empty?
  end

  def shouting?()
    @phrase.upcase == @phrase
  end

  def question?()
    @phrase.end_with?('?')
  end

end
