class Bob

  def hey(incoming_phrase)
    phrase = Phrase.new(incoming_phrase)
    case
    when phrase.silence?
      'Fine. Be that way.'
    when phrase.shouting?
      'Woah, chill out!'
    when phrase.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  class Phrase

    def initialize(phrase)
      @phrase = phrase
    end

    def silence?
      @phrase.to_s.empty?
    end

    def shouting?
      @phrase == @phrase.upcase
    end

    def question?
      @phrase.end_with? '?'
    end
  end
end
