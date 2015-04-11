class Bob
  def hey(message)
    [NothingResponse.new, YellResponse.new,
     QuestionResponse.new, DefaultResponse.new].detect do |response|
      response.match? message
    end.respond
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
      message.nil? || message.gsub(/\s+/, '').empty?
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
