module TeenResponseSystem
  class RespondsToLeetRequest
    def self.responds_to?(message)
      message[/\ABro, /]
    end

    def self.response(message)
      response = message[/\ABro, (.*)/, 1]
      convert response
    end

    private

    def self.convert(message)
      charmap = {'a' => '4',
                 'e' => '3',
                 'l' => '1',
                 't' => '7'}

      message.split('').map {|c| charmap[c] ? charmap[c] : c}.join
    end
  end

  class RespondsToQuestion
    def self.responds_to?(message)
      message[-1] == '?'
    end

    def self.response(message)
      'Sure.'
    end
  end

  class RespondsToSilence
    def self.responds_to?(message)
      message == ''
    end

    def self.response(message)
      'Fine. Be that way.'
    end
  end

  class RespondsToStatement
    def self.responds_to?(message)
      true
    end

    def self.response(message)
      'Whatever.'
    end
  end

  class RespondsToYelling
    def self.responds_to?(message)
      message == message.upcase
    end

    def self.response(message)
      'Woah, chill out!'
    end
  end

  def self.get_response(message)
    responders = [RespondsToSilence,
                  RespondsToLeetRequest,
                  RespondsToQuestion,
                  RespondsToYelling,
                  RespondsToStatement]

    responders.find {|r| r.responds_to? message}.response message
  end
end

class Bob
  def hey(message)
    TeenResponseSystem.get_response message.chomp
  end
end
