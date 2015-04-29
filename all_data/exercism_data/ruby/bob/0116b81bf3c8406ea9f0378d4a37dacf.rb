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

    def initialize(text)
      @text = text
    end

    def silence?
      @text.to_s.empty?
    end

    def shouting?
      @text == @text.upcase
    end

    def question?
      @text.end_with? '?'
    end
  end
end
