class Bob

  def hey(input)
    @input = input
    
    if nothing?
    "Fine. Be that way."
    elsif yelling?
      "Woah, chill out!"
    elsif questioning?
      "Sure."
    else
      "Whatever."
    end
  end

  def yelling?
    @input == @input.upcase
  end

  def questioning?
    @input.end_with? "?"
  end

  def nothing?
    @input == "" or @input == nil
  end
end
