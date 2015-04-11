module TeenResponseSystem
  class RespondsToLeetRequest
    def responds_to?(message)
      @message = message[/\ABro, (.*)/, 1]
      @message
    end

    def response
      convert
    end

    private

    def convert
      charmap = {'a' => '4',
                 'e' => '3',
                 'l' => '1',
                 't' => '7'}

      converted_array = @message.split('').map do |c|
        downc = c.downcase
        charmap[downc] ? charmap[downc] : c
      end

      converted_array.join
    end
  end


  class RespondsToQuestion
    def responds_to?(message)
      message.end_with? '?'
    end

    def response
      'Sure.'
    end
  end


  class RespondsToSilence
    def responds_to?(message)
      message.empty?
    end

    def response
      'Fine. Be that way.'
    end
  end


  class RespondsToStatement
    def responds_to?(message)
      true
    end

    def response
      'Whatever.'
    end
  end


  class RespondsToYelling
    def responds_to?(message)
      message == message.upcase
    end

    def response
      'Woah, chill out!'
    end
  end


  def self.get_response(message)
    responders = RespondsToSilence,
                 RespondsToLeetRequest,
                 RespondsToQuestion,
                 RespondsToYelling,
                 RespondsToStatement

    responders.map{|r| r.new}
      .find {|r| r.responds_to? message}
      .response
  end
end

class Bob
  def hey(message)
    TeenResponseSystem.get_response message.chomp
  end
end
