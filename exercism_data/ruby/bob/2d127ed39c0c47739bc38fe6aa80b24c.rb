  class Bob
    def hey(input)
      sentence = Sentence.new(input)

      case
      when sentence.silence?
        "Fine. Be that way!"
      when sentence.shouting?
        "Woah, chill out!"
      when sentence.question?
        "Sure."
      else
        "Whatever."
      end
    end
  end
  
  class Sentence
    def initialize(phrase)
      @phrase = phrase.to_s.strip
    end

    def silence?
      @phrase.empty?
    end

    def shouting?
      !silence? && @phrase.upcase == @phrase
    end

    def question?
      @phrase.end_with?("?")
    end
  end
  
