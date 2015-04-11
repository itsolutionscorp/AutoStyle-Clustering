class Bob

  def hey(input)
    @input = input
    
    if silence
      "Fine. Be that way!"
    elsif yelling
      "Woah, chill out!"
    elsif question
      "Sure."
    else
      'Whatever.'
    end
  end

  def silence
    if @input.strip == ""
  end

  def yelling
    @input == @input.upcase
  end

  def question
    @input.end_with? ("?")
  end


end
