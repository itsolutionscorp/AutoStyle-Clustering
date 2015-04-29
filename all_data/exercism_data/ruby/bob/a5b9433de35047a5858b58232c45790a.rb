class Bob
  def hey(saying)
    retort Phrase.new(saying)
  end

  private

  def retort(phrase)
    case
    when phrase.silent?
      "Fine. Be that way."
    when phrase.question?
      "Sure."
    when phrase.shout?
      "Woah, chill out!"
    else
      "Whatever."
    end
  end

  class Phrase

    def initialize(phrase)
      @phrase = phrase.to_s
    end

    def silent?
      @phrase.empty?
    end

    def question?
      @phrase.end_with? "?"
    end

    def shout?
      @phrase == @phrase.upcase
    end
  end

end
