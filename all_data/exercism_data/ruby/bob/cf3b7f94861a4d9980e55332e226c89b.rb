class Bob
  def hey(hey_str)
    if !hey_str.index(/[a-zA-Z]/)
      "Fine. Be that way!"
    elsif  hey_str == hey_str.upcase
      "Woah, chill out!"
    elsif hey_str[-1] == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
