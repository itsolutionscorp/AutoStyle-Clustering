class Bob
  def hey(statement)
    if shout? statement
      "Woah, chill out!"
    elsif question? statement
      "Sure."
    elsif silence? statement
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private

  def shout?(statement)
    statement.match(/[A-Z]/) && statement == statement.upcase
  end

  def question?(statement)
    statement.end_with? "?"
  end

  def silence?(statement)
    statement.strip == ""
  end
end
