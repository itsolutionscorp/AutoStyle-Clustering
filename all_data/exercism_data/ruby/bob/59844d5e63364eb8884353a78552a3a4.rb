class Bob
  def hey(message)
    respond_to(Request.new(message))
  end

  private

  def respond_to(request)
    if request.silence?
      'Fine. Be that way!'
    elsif request.shouting?
      'Woah, chill out!'
    elsif request.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  class Request
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

    private

    attr_accessor :message
  end
end
