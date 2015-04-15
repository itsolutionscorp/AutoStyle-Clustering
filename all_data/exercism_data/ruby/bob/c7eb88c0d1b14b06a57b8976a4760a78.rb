class Bob
  def hey(message)
    find_response(message).respond
  end

  def responses
    [NothingResponse.new, YellResponse.new, QuestionResponse.new, DefaultResponse.new]
  end

  def find_response(message)
    responses.detect { |response| response.match? message.to_s }
  end

  class DefaultResponse
    def match?(message)
      true
    end

    def respond
      'Whatever.'
    end
  end

  class NothingResponse
    def match?(message)
      message.strip.empty?
    end

    def respond
      'Fine. Be that way!'
    end
  end

  class YellResponse
    def match?(message)
      message == message.upcase
    end

    def respond
      'Woah, chill out!'
    end
  end

  class QuestionResponse
    def match?(message)
      message.end_with? '?'
    end

    def respond
      'Sure.'
    end
  end
end
