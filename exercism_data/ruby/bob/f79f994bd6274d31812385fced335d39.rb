class Bob
  class YelledPhrase
    def appropriate_for?(input)
      input == input.upcase && input =~ /[A-Z]/
    end

    def response
      'Woah, chill out!'
    end
  end

  class QuestionPhrase
    def appropriate_for?(input)
      input.end_with?('?')
    end

    def response
      'Sure.'
    end
  end

  class EmptyPhrase
    def appropriate_for?(input)
      input.strip.empty?
    end

    def response
      'Fine. Be that way!'
    end
  end

  class FallbackPhrase
    def appropriate_for?(_)
      true
    end

    def response
      'Whatever.'
    end
  end

  PHRASES = [YelledPhrase, QuestionPhrase, EmptyPhrase, FallbackPhrase].map(&:new)

  def hey(input)
    PHRASES.find { |phrase| phrase.appropriate_for?(input) }.response
  end
end
