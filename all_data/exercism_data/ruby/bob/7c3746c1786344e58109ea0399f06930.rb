class Bob
  def hey(msg)
    Response.for(Message.new(msg)).to_s
  end
  
  private
  
  class Message
    attr_reader :msg
    
    def initialize(msg)
      @msg = msg
    end
    
    def questioning?
      msg.end_with?('?')
    end
    
    def shouting?
      msg.upcase == msg
    end
    
    def silent?
      msg.nil? || msg.empty?
    end
  end
  
  class Response
    attr_reader :msg
    
    def initialize(msg)
      @msg = msg
    end
    
    def self.for(msg)
      new(msg).to_s
    end
    
    def to_s
      if msg.silent?
        'Fine. Be that way!'
      elsif msg.shouting?
        'Woah, chill out!'
      elsif msg.questioning?
        'Sure.'
      else
        'Whatever.'
      end
    end
  end
end
