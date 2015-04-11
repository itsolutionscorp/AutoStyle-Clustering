class Bob
  def hey(message)
    response = [NothingResponse, YellResponse,
     QuestionResponse, DefaultResponse].detect do |response|
      response.match? message
    end

    response.new.respond
  end

  class DefaultResponse
    def self.match?(message)
      true
    end

    def respond
      'Whatever.'
    end
  end

  class NothingResponse
    def self.match?(message)
      message.nil? || message.strip.empty?
    end

    def respond
      'Fine. Be that way!'
    end
  end

  class YellResponse
    def self.match?(message)
      message == message.upcase
    end

    def respond
      'Woah, chill out!'
    end
  end

  class QuestionResponse
    def self.match?(message)
      message.end_with? '?'
    end

    def respond
      'Sure.'
    end
  end
end
