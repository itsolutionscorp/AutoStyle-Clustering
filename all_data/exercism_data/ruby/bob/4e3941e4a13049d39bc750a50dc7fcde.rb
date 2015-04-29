class Bob

  REPLIES = {
    silence:  'Fine. Be that way!',
    shouting: 'Woah, chill out!',
    question: 'Sure.',
    other:    'Whatever.',
  }


  def hey(phrase)
    REPLIES[PhraseAnalyser.phrase_type(phrase)]
  end

end


module PhraseAnalyser

  class << self
    def phrase_type(phrase)
      if silence?(phrase)
        :silence
      elsif shouting?(phrase)
        :shouting
      elsif question?(phrase)
        :question
      else
        :other
      end
    end

    def silence?(phrase)
      phrase.nil? || phrase.strip.empty?
    end

    def shouting?(phrase)
      phrase == phrase.upcase
    end

    def question?(phrase)
      phrase.end_with?('?')
    end

  end
end
