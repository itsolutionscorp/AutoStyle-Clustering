class Bob

  def hey(str)
    get_response str.strip
  end

  def get_response(str)
    if is_shouting?(str)
      "Woah, chill out!"
    elsif is_asking?(str)
      "Sure."
    elsif is_silent?(str)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  def is_shouting?(str)
    str == str.upcase && str =~/[A-Z]+/
  end

  def is_asking?(str)
    str.end_with?('?')
  end

  def is_silent?(str)
    str == ""
  end

end
