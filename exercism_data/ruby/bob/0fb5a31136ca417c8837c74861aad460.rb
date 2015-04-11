class Bob

  class Dialog

    def initialize(message)
      @message = String(message)
    end

    def reply
      case
      when provocative? then "Fine. Be that way."
      when yielled? then "Woah, chill out!"
      when interrogative? then "Sure."
      else "Whatever."
      end
    end

    private    
    def provocative?
      @message.empty?
    end
    
    def yielled?
      @message == @message.upcase
    end

    def interrogative?
      @message.end_with? '?'
    end
    
  end

  def hey(message)
    Dialog.new(message).reply
  end

end
