class Bob
  def hey(saywhat)
    aStatement = Statement.new(saywhat)
    respond_to(aStatement)
  end

  def respond_to(statement)
    case 
    when statement.silence?
      "Fine. Be that way!"
    when statement.yelling?
      "Woah, chill out!"
    when statement.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Statement
  def initialize(saywhat)
    @saywhat = String(saywhat)
  end

  def silence?
    @saywhat.strip.empty?
  end
  def yelling?
    @saywhat == @saywhat.upcase
  end
  def question?
    @saywhat.end_with?("?")
  end
end
