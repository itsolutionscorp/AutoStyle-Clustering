class Bob
  def hey(words)
    sentence = Sentence.new(words)
    if sentence.silence?
      "Fine. Be that way!"
    elsif sentence.shouting?
      "Woah, chill out!"
    elsif sentence.question?
      "Sure."
    else
      "Whatever."
    end
  end

  class Sentence
    attr_reader :words

    def initialize(words)
      @words = words
    end

    def silence?
      words.to_s.strip.empty?
    end

    def shouting?
      words.upcase == words
    end

    def question?
      words.end_with? "?"
    end
  end
end
