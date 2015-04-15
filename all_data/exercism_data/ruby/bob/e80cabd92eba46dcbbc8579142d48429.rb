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
    @string == nil || @string == "" || @string.chars.each{ |x| break if x != " " }
  end

  def question?
    @string.chars.last == "?"
  end

  def capitalized?
    @string == @string.upcase
  end
end
