class Bob

  def hey(incoming_phrase)
    phrase = Phrase.new(incoming_phrase)
    if phrase.silence?
      'Fine. Be that way.'
    elsif phrase.shouting?
      'Woah, chill out!'
    elsif phrase.question?
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
