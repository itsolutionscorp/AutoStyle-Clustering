class Bob
  class Statement
    def initialize(text)
      @text = text.to_s
    end

    def say_anything?() !@text.empty?  end
    
    def yelling?() @text == @text.upcase end

    def question?() @text.end_with?("?") end

  end

  def hey(statement) 
    statement = Statement.new(statement)
    if    not statement.say_anything?  then "Fine. Be that way!"
    elsif statement.yelling?           then "Woah, chill out!"
    elsif statement.question?          then "Sure."
    else                                    "Whatever."
    end
  end

end
