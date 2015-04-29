class Bob

  def hey(string)
    @string = string

    if blank?
      return "Fine. Be that way!"
    elsif capitalized?
      return "Woah, chill out!"
    elsif question?
      return "Sure."
    else
      "Whatever."
    end
  end

  def blank?
    @string == nil || @string.strip == ""
  end

  def question?
    @string.end_with? "?"
  end

  def capitalized?
    @string == @string.upcase
  end
end
