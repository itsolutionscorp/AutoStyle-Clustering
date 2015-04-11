class Statement
  def initialize statement
    @statement = statement || ""
  end

  def shouting?
    @statement.upcase == @statement
  end

  def question?
    @statement.end_with? "?"
  end

  def silent?
    @statement.empty?
  end
end

class Teenager
  def hey sentence
    statement = Statement.new(sentence)
    if statement.silent?
      "Fine. Be that way!"
    elsif statement.shouting?
      "Woah, chill out!" 
    elsif statement.question?
      "Sure." 
    else 
      "Whatever."
    end 
  end
end

class Bob < Teenager
end
