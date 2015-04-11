class Bob
  def hey(message)
    best_applicable_responder(message).response
  end

  private

  def best_applicable_responder(message)
    responders.detect{ |r| r.applicable_to?(message) }
  end

  def responders
    @responders ||= [
      BlankResponder.new, ShoutingResponder.new, QuestionResponder.new,
      DefaultResponder.new
    ]
  end

  class BlankResponder
    def applicable_to?(message)
      message.nil? || message.strip.empty?
    end

    def response
      'Fine. Be that way!'
    end
  end

  class ShoutingResponder
    def applicable_to?(message)
      message.upcase == message
    end

    def response
      'Woah, chill out!'
    end
  end

  class QuestionResponder
    def applicable_to?(message)
      message.end_with?('?')
    end

    def response
      'Sure.'
    end
  end

  class DefaultResponder
    def applicable_to?(message)
      true
    end

    def response
      'Whatever.'
    end
  end
end
