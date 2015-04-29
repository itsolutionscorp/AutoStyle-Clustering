class Bob
  def hey(hey_str)
    if hey_str.index(/[A-Z]{2,}/) && (hey_str.count("a-z") == 0)
      "Woah, chill out!"
    elsif (hey_str[-1] == "?") && (hey_str.count("a-z") > 0)
      "Sure."
    elsif hey_str.index(/[\%\-\?\']/)
      "Whatever."
    else
      "Fine. Be that way!"
    end
  end
end
