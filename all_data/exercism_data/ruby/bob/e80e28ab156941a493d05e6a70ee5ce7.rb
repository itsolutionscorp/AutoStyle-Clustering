class Bob

  def hey something
    if something.strip.empty?
      "Fine. Be that way!"
    elsif something.upcase == something && something.upcase != something.downcase
      "Woah, chill out!" 
    elsif something[-1] == "?"
      "Sure."
    else 
      "Whatever."
    end
  end

end
