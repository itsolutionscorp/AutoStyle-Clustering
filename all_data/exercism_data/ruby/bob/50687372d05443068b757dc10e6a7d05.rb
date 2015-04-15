class Bob

  def hey(text)
    phrase = Phrase.new(text)
    if phrase.silence?
      'Fine. Be that way!'
    elsif phrase.shouting?
      'Woah, chill out!'
    elsif phrase.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  class Phrase

    def initialize(text)
      @text = text
    end

    def silence?
      text.nil? || text.strip.empty?
    end

    def shouting?
      text.upcase == text
    end

    def question?
      text.end_with?("?")
    end

    private

      attr_reader :text

  end

end
