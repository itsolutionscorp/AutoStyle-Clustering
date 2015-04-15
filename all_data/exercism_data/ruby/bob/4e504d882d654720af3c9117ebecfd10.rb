require "delegate"

class Statement < SimpleDelegator
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
  def hey(statement_string)
    statement = Statement.new statement_string

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
