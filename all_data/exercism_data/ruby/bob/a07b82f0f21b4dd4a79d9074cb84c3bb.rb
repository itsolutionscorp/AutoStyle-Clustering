class Bob

  def hey(statement_string)
    statement = Statement.new(statement_string) 
    if statement.is_nothing?
      'Fine. Be that way!'
    elsif statement.is_shouted?
      'Woah, chill out!'
    elsif statement.is_question?
      'Sure.' 
    else
      'Whatever.'
    end
  end

end


class Statement 
    def initialize(statement_string)
        @statement = statement_string 
    end

    def is_question?
        @statement[-1,1] == '?'
    end

    def is_shouted?
        @statement.upcase == @statement
    end

    def is_nothing?
      @statement.nil? || @statement.lstrip.empty? 
    end 

end
