class Bob

  def hey(something)
    if !something.strip.empty? && something.upcase == something
      "Woah, chill out!"
    elsif something[-1] == "?"
      "Sure."
    elsif something.strip.empty?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
