class Bob

  def hey(phrase)
    Brain.new(phrase).response
  end

  class Brain
    DEFAULT_ANSWER = 'Whatever.'
    attr_reader :phrase

    def initialize(phrase)
      @phrase = phrase
    end

    def touching?
      phrase.nil? || phrase.empty?
    end

    def question?
      phrase.end_with?('?')
    end

    def shouting?
      phrase.upcase == phrase
    end    

    def response
      return 'Fine. Be that way.' if touching?
      return 'Sure.'              if question?
      return 'Woah, chill out!'   if shouting?
      return DEFAULT_ANSWER
    end
  end
end
