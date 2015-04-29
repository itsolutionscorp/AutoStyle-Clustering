# Playing around with delegation for this solution.
# Turns out, this is a very effective way to overcomplicate a simple problem.

class Bob
  def initialize(responder_chain = DEFAULT_RESPONDER_CHAIN)
    @responder_chain = responder_chain
  end

  def hey(input)
    @responder_chain.respond(input)
  end

  class Responder
    def initialize(next_responder)
      @next_responder = next_responder
    end

    def can_respond?(input)
      raise NotImplementedError
    end

    def response
      raise NotImplementedError
    end

    def respond(input)
      return self.response if self.can_respond?(input)
      @next_responder.respond(input)
    end
  end

  class QuestionResponder < Responder
    def can_respond?(input)
      return input.end_with?("?")
    end

    def response
      "Sure."
    end
  end

  class EmptyResponder < Responder
    def can_respond?(input)
      input.nil? || input.strip.empty?
    end

    def response
      "Fine. Be that way!"
    end
  end

  class ShoutResponder < Responder
    def can_respond?(input)
      input == input.upcase
    end

    def response
      "Woah, chill out!"
    end
  end

  class DefaultResponder < Responder
    def initialize(next_responder = nil)
      unless next_responder.nil?
        puts "Throwing away DefaultResponder initializer next_responder"
      end
      super(nil)
    end

    def can_respond?(input)
      true
    end

    def response
      "Whatever."
    end
  end

private
  DEFAULT_RESPONDER_CHAIN = EmptyResponder.new(
                              ShoutResponder.new(
                                QuestionResponder.new(
                                  DefaultResponder.new)))
end
