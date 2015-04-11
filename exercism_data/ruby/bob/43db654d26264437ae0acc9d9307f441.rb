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
    def initialize(s)
      @s = s
    end

    def silence?
      @s.nil? || @s.empty?
    end

    def question?
      @s.end_with?('?')
    end

    def shouting?
      @s.upcase == @s
    end
  end

end
