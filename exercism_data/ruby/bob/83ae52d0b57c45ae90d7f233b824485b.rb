class Bob
  def hey(input)
    if is_nothing?(input)
      "Fine. Be that way!"
    elsif is_capslocked?(input)
      "Woah, chill out!"
    elsif is_a_question?(input)
      "Sure."
    else
      "Whatever."
    end
  end

  def is_capslocked?(input)
    input.upcase == input
  end

  def is_nothing?(input)
    input.nil? || input.empty? || input.include?("  ")
  end

  def is_a_question?(input)
    input.end_with?("?")
  end
end
