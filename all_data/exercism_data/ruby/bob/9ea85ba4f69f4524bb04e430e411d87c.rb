class Bob
  def hey(message)
    responder = Responder.new(message)

    responder.to_s
  end

  class Responder
    def initialize(message)
      @message = message
      process_response
    end

    def response
      @response || 'Whatever.'
    end
    alias :to_s :response

    private

    def process_response
      if empty?
        @response = 'Fine. Be that way.'
      elsif shouted?
        @response = 'Woah, chill out!'
      elsif question?
        @response = 'Sure.'
      end
    end

    def empty?
      @message.empty?
    end

    def question?
      last_char = @message[-1]
      last_char == '?'
    end

    def shouted?
      message_letters = @message.scan(/[a-z]+/i)

      message_letters.all? do |letter_block|
        letter_block.match(/\p{Upper}+/)
      end
    end
  end
end
