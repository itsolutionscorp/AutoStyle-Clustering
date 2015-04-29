class Bob
  def hey(input)
    statement = Statement.new(input)
    if statement.shout?
      "Woah, chill out!"
    elsif statement.slient_treatment?
      "Fine. Be that way!"
    elsif statement.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Statement
  def initialize(input)
    @input = input
  end

  def shout?
    @input.match(/\p{L}/) && @input.upcase == @input
  end

  def slient_treatment?
    @input.gsub(/\s/, "").empty?
  end

  def question?
    @input[-1] == "?"
  end
end
