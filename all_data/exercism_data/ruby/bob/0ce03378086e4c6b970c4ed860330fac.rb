module Statement
  def shouting? statement
    statement.upcase == statement
  end

  def question? statement
    statement.end_with? "?"
  end

  def silent? statement
    statement.to_s.empty?
  end
end

class Bob
  include Statement
  def hey sentence
    if silent?(sentence)
      "Fine. Be that way!"
    elsif shouting?(sentence)
      "Woah, chill out!" 
    elsif question?(sentence)
      "Sure." 
    else 
      "Whatever."
    end 
  end
end
