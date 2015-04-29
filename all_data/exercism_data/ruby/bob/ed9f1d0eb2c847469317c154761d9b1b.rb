class Bob
  def hey(message)
    Responder.new(message).response
  end

  class Responder
    attr_accessor :message
    
    def initialize(message)
      self.message = message
    end

    def response
      if message == ""
        "Fine. Be that way!"
      elsif message == message.upcase
        "Woah, chill out!"
      elsif message.end_with? "?"
        "Sure."
      else
        "Whatever."
      end
    end
  end
end
