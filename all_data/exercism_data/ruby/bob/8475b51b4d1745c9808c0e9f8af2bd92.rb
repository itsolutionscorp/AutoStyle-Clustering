class Bob

  def hey(phrase)

      if PhraseAnalyzer.silence?(phrase)
        'Fine. Be that way!'
      elsif PhraseAnalyzer.shouting?(phrase)
        'Woah, chill out!'
      elsif PhraseAnalyzer.question?(phrase)
        'Sure.'
      else 
        'Whatever.'
      end

  end
    
end


class PhraseAnalyzer

  def self.silence?(phrase)
    phrase.to_s.strip.empty?
  end

  def self.shouting?(phrase)
    phrase.upcase == phrase
  end

  def self.question?(phrase)
    phrase.end_with?('?')
  end

end
