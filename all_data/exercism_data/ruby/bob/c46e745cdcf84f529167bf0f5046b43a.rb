class Bob
  def hey words
    respond_to Sentence.new words
  end

  private

  def respond_to sentence
    if sentence.silent?
      "Fine. Be that way!"
    elsif sentence.shout?
      "Woah, chill out!"
    elsif sentence.question?
      "Sure."
    else
      "Whatever."
    end
  end

  private

  class Sentence
    def initialize words
      @words = words.to_s
    end

    def silent?
      @words == ""
    end

    def shout?
      !silent? && @words == @words.upcase
    end

    def question?
      @words.end_with? "?"
    end
  end
end
