class Bob
  def hey(message)
    Request.new(message).respond
  end

  class Request
    def respond
      if empty?
        'Fine. Be that way!'
      elsif all_caps?
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

    def empty?
      message.empty?
    end

    def all_caps?
      !message.empty? && message.upcase == message
    end

    def question?
      message.end_with?('?')
    end
  end
end
