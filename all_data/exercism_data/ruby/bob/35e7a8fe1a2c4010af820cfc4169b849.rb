class Bob
  class Phrase
    def initialize(text)
      @text = text
    end

    def actual_saying?
      @text.match(/\w/)
    end

    def loud?
      @text.match(/[A-Z]/) && !@text.match(/[a-z]/)
    end

    def question?
      @text.strip.end_with?('?')
    end
  end

  def hey(phrase_text)
    phrase = Phrase.new(phrase_text)
    if !phrase.actual_saying?
      'Fine. Be that way!'
    elsif phrase.loud?
      'Woah, chill out!'
    elsif phrase.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end
