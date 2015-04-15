class Bob
  def hey(string)
    if string.strip == ""
      "Fine. Be that way!"
    elsif string.upcase == string
      "Woah, chill out!"
    elsif string[-1,1] == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
