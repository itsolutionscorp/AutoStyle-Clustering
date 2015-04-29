class String

  def shouting?
    upcase == self
  end

  def question?
    end_with? '?'
  end

end

class Bob

  def hey(statement)
    if statement.nil? || statement.empty?
      "Fine. Be that way."
    elsif statement.shouting?
      "Woah, chill out!"
    elsif statement.question?
      "Sure."
    else
      "Whatever."
    end
  end

end
