class Bob
  def hey(phrase)
    phrase = Phrase.new(phrase)
    return "Fine. Be that way!" if phrase.empty?
    return "Woah, chill out!" if phrase.yelling?
    return "Sure." if phrase.question?
    "Whatever."
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
  end
end
