class Bob
  def hey(phrase)
    phrase = Phrase.new(phrase)
    phrase.answer
  end

  class Phrase
    def initialize(content)
      @content = content
    end

    def empty?
      @content.strip.empty?
    end

    def yelling?
      @content == @content.upcase
    end

    def question?
      @content =~ /.+\?$/
    end

    def answer
      return "Fine. Be that way!" if empty?
      return "Woah, chill out!" if yelling?
      return "Sure." if question?
      "Whatever."
    end
  end
end
