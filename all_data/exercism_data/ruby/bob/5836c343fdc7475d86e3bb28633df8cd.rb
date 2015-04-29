class Bob
  def hey(message)
    Handlers.methods.find {|h| Handlers.send(message) }
  end
      
  module Handlers
    def silence(message)
      "Fine. Be that way!" if @message.strip.empty?
    end

    def shouting(message)
      "Woah, chill out!" if @message.upcase == @message
    end
    
    def questioning(message)
      "Sure." if @message.end_with?("?")
    end

    def default(message)
      "Whatever."
    end
  end
end
