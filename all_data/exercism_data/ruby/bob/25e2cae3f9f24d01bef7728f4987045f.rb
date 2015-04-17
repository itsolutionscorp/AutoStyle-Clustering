require "delegate"

class Bob

  def hey(input)
    sentence = SentenceToBob.new(input)
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

private

  class SentenceToBob < SimpleDelegator
    def silence?
      strip.empty?
    end

    def has_words?
      match(/[a-zA-Z]/)
    end

    def question?
      end_with? "?"
    end

    def shouting?
      has_words? && upcase == self
    end
  end

end