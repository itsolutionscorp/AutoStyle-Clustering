class Bob

  def hey(phrase)

    case PhraseAnalyser.phrase_type(phrase)
    when :silence
      'Fine. Be that way!'
    when :shouting
      'Woah, chill out!'
    when :question
      'Sure.'
    when :other
      'Whatever.'
    end

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
