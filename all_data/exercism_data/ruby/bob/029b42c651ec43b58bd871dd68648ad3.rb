class Bob
 
  def hey(input)
    @input = input.nil? ? "" : input
    if silent?
      "Fine. Be that way!"
    elsif shouting? then
      "Woah, chill out!"
    elsif questioning?
      "Sure."
    else
      "Whatever."
    end
  end

private
  def shouting?
    @input.upcase.eql?(@input)
  end

  def questioning?
    @input.end_with? "?"
  end

  def silent?
    @input.strip.empty?
  end

end
