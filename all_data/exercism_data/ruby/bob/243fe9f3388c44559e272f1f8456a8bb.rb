class Bob

  def hey(statement)
    Response.new(statement).reply
  end

  class Response
    
    REPLIES = {
      question: 'Sure.',
      shouting: 'Woah, chill out!',
      silence: 'Fine. Be that way!',
      statement: 'Whatever.'
    }

    def initialize(statement)
      @statement = statement
    end
      
    def reply
      REPLIES[reply_type]
    end

    private

    def reply_type
      if silence?
        :silence
      elsif shouting?
        :shouting
      elsif question?
        :question
      else
        :statement
      end
    end

    def shouting?
      @statement.upcase == @statement
    end
  
    def question?
      @statement.end_with? '?'
    end
  
    def silence?
      @statement.to_s.strip.empty?
    end
  
    def statement?
      true
    end

  end

end
