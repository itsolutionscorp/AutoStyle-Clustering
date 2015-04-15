class Bob

  def hey(input)
    if nothing_said(input)
      "Fine. Be that way."
    elsif question(input)
      "Sure."
    elsif shouting(input)
      "Woah, chill out!"
    else
      "Whatever."
    end
  end

  def nothing_said(input)
    input.empty?
  end

  def question(input)
    input.end_with?('?')
  end

  def shouting(input)
    input == input.upcase
  end
end
