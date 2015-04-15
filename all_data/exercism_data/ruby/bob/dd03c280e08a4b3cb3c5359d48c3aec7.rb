class Bob
 
  def hey(input)
    @input = input
    if shouting? then
      "Woah, chill out!"
    elsif questioning?
      "Sure."
    elsif silent?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  def shouting?
    !silent? && @input.upcase.eql?(@input)
  end

  def questioning?
    @input.end_with? "?"
  end

  def silent?
    @input.strip.empty?
  end

end
