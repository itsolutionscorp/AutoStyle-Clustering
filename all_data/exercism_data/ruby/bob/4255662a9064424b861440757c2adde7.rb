class Bob
  def hey(message)
    message = Message.new message
    message.response
  end

  class Message
    attr_accessor :message

    def initialize(message)
      self.message = message
    end

    def response
      if empty?
        'Fine. Be that way.'
      elsif question?
        'Sure.'
      elsif shout?
        'Woah, chill out!'
      else
        'Whatever.'
      end
    end

    private

    def empty?
      message.nil? || message.empty?
    end

    def question?
      message[-1] == '?'
    end

    def shout?
      !statement?
    end

    def statement?
      message =~ /[a-z]/
    end
  end
end
