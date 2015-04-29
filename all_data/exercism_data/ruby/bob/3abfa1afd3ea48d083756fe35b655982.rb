class Bob
  def hey(message)
    Request.new(message).respond
  end

  class Request
    def respond
      if silence?
        'Fine. Be that way!'
      elsif shouting?
        'Woah, chill out!'
      elsif question?
        'Sure.'
      else
        'Whatever.'
      end
    end

    private

    attr_accessor :message

    def initialize(message)
      self.message = (message || '').strip
    end

    def silence?
      message.empty?
    end

    def shouting?
      !message.empty? && message.upcase == message
    end

    def question?
      message.end_with?('?')
    end
  end
end
