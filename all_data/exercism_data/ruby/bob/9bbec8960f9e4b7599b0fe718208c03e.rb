class Bob

  def hey(statement)
    if yelling?(statement)
      "Whoa, chill out!"
    elsif silence?(statement)
      "Fine. Be that way!"
    elsif question?(statement)
      "Sure."
    else
      "Whatever."
    end
  end

  def yelling?(statement)
    statement == statement.upcase && statement != statement.downcase
  end

  def question?(statement)
    statement.end_with?('?')
  end

  def silence?(statement)
    statement.strip.empty?
  end

end
