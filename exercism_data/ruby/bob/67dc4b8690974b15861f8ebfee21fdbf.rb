class Bob
  def hey(saywhat)
    aStatement=Statement.new(saywhat)
    aStatement.respond_to
  end
end

class Statement
  def respond_to
    case 
    when silence?
      "Fine. Be that way!"
    when yelling?
      "Woah, chill out!"
    when question?
      "Sure."
    else
      "Whatever."
    end
  end

  def initialize(saywhat)
    @saywhat=String(saywhat)
  end

  private
  def silence?
    @saywhat.strip.empty?
  end
  def yelling?
    @saywhat == @saywhat.upcase()
  end
  def question?
    @saywhat.end_with?("?")
  end
end
