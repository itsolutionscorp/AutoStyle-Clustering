class Bob
  class Responder
    def self.responds? message
      true
    end
    def self.response
      "Whatever."
    end
  end
  
  class Mute < Responder
    def self.responds? message
      !message || message.strip.empty?
    end
    def self.response
      "Fine. Be that way!"
    end
  end
  
  class Shout < Responder
    def self.responds? message
      message.upcase == message
    end
    def self.response
      "Woah, chill out!"
    end
  end
  
  class Query < Responder
    def self.responds? message
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
    get_responder(message).response
  end
  
  private
  def get_responder message
    RESPONSES.find(->{Responder}) {|responder| responder.responds? message }
  end
end
