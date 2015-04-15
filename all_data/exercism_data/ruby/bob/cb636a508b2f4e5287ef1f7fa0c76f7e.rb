class Bob
  def hey str
    if str.swapcase != str && str.upcase == str
      "Woah, chill out!"
    elsif str[-1] == "?"
      "Sure."
    elsif str.strip == ""
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
