class Bob
  def hey(text)
    if text.strip.empty?
      "Fine. Be that way!"
    elsif text == text.upcase
      "Woah, chill out!"
    elsif text[-1] == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
