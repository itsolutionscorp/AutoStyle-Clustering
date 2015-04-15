class String
  def shout?
    match(/[A-Z]/) && self == upcase
  end

  def question?
    end_with? "?"
  end

  def silence?
    strip == ""
  end
end

class Bob
  def hey(statement)
    if statement.shout?
      "Woah, chill out!"
    elsif statement.question?
      "Sure."
    elsif statement.silence?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
