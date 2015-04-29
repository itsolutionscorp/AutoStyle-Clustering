class Bob

  def hey(query)
    if blank?(query)
      "Fine. Be that way!"
    elsif allcaps?(query) && !contains_only_numbers?(query)
      "Woah, chill out!"
    elsif query.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end

  def allcaps?(query)
    query == query.upcase
  end

  def contains_only_numbers?(query)
    query =~ /\A(\d|\s|,|\?)*\z/
  end

  def blank?(query)
    query =~ /\A\s*\z/
  end
end
