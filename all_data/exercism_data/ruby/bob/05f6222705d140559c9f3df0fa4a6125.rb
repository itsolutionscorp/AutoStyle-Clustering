class Bob
  def hey text
    LackadaisicalResponse.new.for text
  end
end

class LackadaisicalResponse
  def for text
    responses.find {|r| r.appropriate? text.to_s }.response
  end

  private

  def responses
    # Order is important
    [
      ToSilence.new,
      ToShouting.new,
      ToQuestion.new,
      ToStatement.new, # always responds
    ]
  end

  class ToStatement
    def response
      'Whatever.'
    end

    def appropriate? text
      true
    end
  end

  class ToQuestion
    def response
      'Sure.'
    end

    def appropriate? text
      text[-1] == ??
    end
  end

  class ToShouting
    def response
      'Woah, chill out!'
    end

    def appropriate? text
      text.upcase == text
    end
  end

  class ToSilence
    def response
      'Fine. Be that way!'
    end

    def appropriate? text
      text.empty?
    end
  end
end
