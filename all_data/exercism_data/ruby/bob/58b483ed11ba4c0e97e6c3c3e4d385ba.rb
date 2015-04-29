class Bob
  def hey(message)
    MessageResponse.new(message).response
  end

  class MessageResponse
    attr_accessor :message

    def initialize(message)
      self.message = message || ''
    end

    def response
      if message_was_silent
        'Fine. Be that way.'
      elsif message_was_loud
        'Woah, chill out!'
      elsif message_was_questioning
        'Sure.'
      else
        'Whatever.'
      end
    end

    private

    def message_was_questioning
      message[-1] == '?'
    end

    def message_was_silent
      message.empty?
    end

    def message_was_loud
      message == message.upcase
    end
  end
end
