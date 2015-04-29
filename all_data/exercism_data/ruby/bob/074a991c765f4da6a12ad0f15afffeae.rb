class Bob
  def hey(saywhat)
    statement = Statement.new(saywhat)
    answer_to(statement)
  end
  def answer_to(statement)
    if statement.silence?
      "Fine. Be that way!"
    elsif statement.yelling?
      "Woah, chill out!"
    elsif statement.question?
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
