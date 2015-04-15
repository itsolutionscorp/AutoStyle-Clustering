class Bob
  def initialize(responder_chain = nil)
    @responder_chain = responder_chain || [EmptyResponder.new, ShoutResponder.new, QuestionResponder.new, DefaultResponder.new]
  end

  def hey(input)
    @responder_chain.find { |responder| responder.can_respond?(input) }.response
  end

  class QuestionResponder
    def can_respond?(input)
      return input.end_with?("?")
    end

    def response
      "Sure."
    end
  end

  class EmptyResponder
    def can_respond?(input)
      input.nil? || input.strip.empty?
    end

    def response
      "Fine. Be that way!"
    end
  end

  class ShoutResponder
    def can_respond?(input)
      input == input.upcase
    end

    def response
      "Woah, chill out!"
    end
  end

  class DefaultResponder
    def can_respond?(input)
      true
    end

    def response
      "Whatever."
    end
  end
end
