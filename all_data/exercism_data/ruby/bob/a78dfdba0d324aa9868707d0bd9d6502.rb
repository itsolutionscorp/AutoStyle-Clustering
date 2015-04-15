class Bob

  def hey(s)
    statement = Statement.new(s)
    case
    when statement.silence?  then 'Fine. Be that way.'
    when statement.question? then 'Sure.'
    when statement.shouting? then 'Woah, chill out!'
    else 'Whatever.'
    end
  end

  private

  class Statement
    def initialize(statement)
      @statement = statement.to_s
    end

    def silence?
      @statement.nil? || @statement.empty?
    end

    def question?
      @statement.end_with?('?')
    end

    def shouting?
      @statement.upcase == @statement
    end
  end

end
