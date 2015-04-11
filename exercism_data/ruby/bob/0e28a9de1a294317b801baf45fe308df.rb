class Bob
  class Responder
    def self.is_type? message
      true
    end
    def self.response
      "Whatever."
    end
  end
  
  class Mute < Responder
    def self.is_type? message
      !message || message.strip.empty?
    end
    def self.response
      "Fine. Be that way!"
    end
  end
  
  class Shout < Responder
    def self.is_type? message
      message.upcase == message
    end
    def self.response
      "Woah, chill out!"
    end
  end
  
  class Query < Responder
    def self.is_type? message
      message.end_with? "?"
    end
    def self.response
      "Sure."
    end
  end
  
  RESPONSES = [
   Mute,
   Shout,
   Query
  ]
  def hey message
    RESPONSES.find(->{Responder}) {|responder| responder.is_type? message }.response
  end
end
