class Bob

  def hey(text)
    phrase = Phrase.new(text)
    return 'Fine. Be that way!' if phrase.silence?
    return 'Woah, chill out!' if phrase.shouting?
    return 'Sure.' if phrase.question?
    "Whatever."
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
