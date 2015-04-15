class Bob
  def hey(saywhat)
    a_statement = Statement.new(saywhat)
    if a_statement.silence?
      "Fine. Be that way!"
    elsif a_statement.yelling?
      "Woah, chill out!"
    elsif a_statement.question?
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
