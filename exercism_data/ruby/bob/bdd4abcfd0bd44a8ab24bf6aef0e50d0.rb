class Bob
  def hey(message)
    Response.new(message).respond
  end

  module ResponseMethods
    def fine
      'Fine. Be that way!' if @message.strip.empty?
    end

    def woah
      'Woah, chill out!' if @message.upcase == @message
    end

    def sure
      'Sure.' if @message.slice(-1) == '?'
    end

    def whatever 
      'Whatever.' if true
    end
  end

  class Response
    include ResponseMethods

    def initialize(message)
      @message = message
    end

    def respond
      ResponseMethods.instance_methods.map{|m| send(m)}.reject(&:nil?).first
    end
  end
end
