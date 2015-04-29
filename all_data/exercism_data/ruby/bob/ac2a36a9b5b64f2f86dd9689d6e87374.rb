class Bob

  def hey(phrase)
    phrase = Phrase.new(phrase)
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

    def initialize(text)
      @text = String(text)
    end

    def silence?
      @text.empty?
    end

    def shouting?
      @text == @text.upcase
    end

    def question?
      @text.end_with? '?'
    end
  end
end
