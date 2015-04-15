class Bob
  def hey(message)
    [NullResponse.new, EmptyResponse.new, YellResponse.new, QuestionMesssage.new, DefaultResponse.new].each do |response|
      if response.match? message
        return response.respond
      end
    end
  end

  class Response
    def match?(message)
      true
    end

    def respond
      'Whatever.'
    end
  end

  class NullResponse < Response
    def match?(message)
      message.nil?
    end

    def respond
      'Fine. Be that way!'
    end
  end

  class EmptyResponse < Response
    def match?(message)
      message.gsub(/\s+/, '').empty?
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

  class QuestionMesssage < Response
    def match?(message)
      message.end_with? '?'
    end

    def respond
      'Sure.'
    end
  end

  class DefaultResponse < Response
  end
end
