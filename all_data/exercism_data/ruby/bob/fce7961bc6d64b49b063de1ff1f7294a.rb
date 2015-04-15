require "delegate"

class Bob

  def hey(input)
    sentence = Sentence.new(input)
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

  class Sentence < SimpleDelegator
    def silence?
      strip.empty?
    end

    def question?
      end_with? "?"
    end

    def shouting?
      has_alphas? && upcase == self
    end

    protected

    def has_alphas?
      match(/[a-zA-Z]/)
    end
  end

end
