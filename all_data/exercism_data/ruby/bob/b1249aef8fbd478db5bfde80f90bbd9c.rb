class Bob
  def hey(text)
    LackadaisicalResponse.for text
  end
end

class LackadaisicalResponse
  def self.for(text)
    new.for text
  end

  def for(text)
    responses.find {|r| r.appropriate_for? text.to_s }.to_s
  end

  private

  def responses
    # Order is important
    [
      ToSilence.new,
      ToShouting.new,
      ToQuestion.new,
      ToAnything.new
    ]
  end

  class ToAnything
    def to_s
      'Whatever.'
    end

    def appropriate_for?(text)
      true
    end
  end

  class ToQuestion
    def to_s
      'Sure.'
    end

    def appropriate_for?(text)
      text[-1] == ??
    end
  end

  class ToShouting
    def to_s
      'Woah, chill out!'
    end

    def appropriate_for?(text)
      text.upcase == text
    end
  end

  class ToSilence
    def to_s
      'Fine. Be that way!'
    end

    def appropriate_for?(text)
      text.empty?
    end
  end
end
