class Bob

  def initialize

  end

  def hey(text)
    if text == text.upcase && text != text.downcase
      "Whoa, chill out!"
    elsif text[-1] == ("?")
    "Sure."
    elsif text.strip == ""
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end


end
