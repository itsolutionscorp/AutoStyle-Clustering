class Bob
  def hey words
    words = words.gsub "\n", ""

    if words.gsub(/\W/i, "") == ""
      fine
    elsif words == words.upcase
      chill_out
    elsif words =~ /\?$/i
      response
    else
      whatever
    end
  end

  private
  def chill_out
    "Woah, chill out!"
  end

  def response
    "Sure."
  end

  def whatever
    "Whatever."
  end

  def fine
    "Fine. Be that way!"
  end
end
