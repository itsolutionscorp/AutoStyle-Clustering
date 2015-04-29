class Bob

  def hey(input)
    @input = input
    
    if hears_nothing?
    "Fine. Be that way."
    elsif is_yelled_at?
      "Woah, chill out!"
    elsif is_questioned?
      "Sure."
    else
      "Whatever."
    end
  end

  def is_yelled_at?
    @input == @input.upcase
  end

  def is_questioned?
    @input.end_with? "?"
  end

  def hears_nothing?
    @input == "" or @input == nil
  end
end
