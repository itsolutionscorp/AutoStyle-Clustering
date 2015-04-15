class Bob

  def hey(statement)
    Response.new(statement).reply
  end

  class Response
    
    def initialize(statement)
      @statement = statement
    end
      
    def reply
      if silence?
        'Fine. Be that way!'
      elsif shouting?
        'Woah, chill out!'
      elsif question?
        'Sure.'
      else
        'Whatever.'
      end
    end

    private

    def shouting?
      @statement.upcase == @statement
    end
  
    def question?
      @statement.end_with? '?'
    end
  
    def silence?
      @statement.to_s.strip.empty?
    end
  
  end

end
