class Bob

  def hey(input)

    case
    when silence?(input)
      "Fine. Be that way!"
    when yelling?(input)
      "Woah, chill out!"
    when question?(input)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def silence?(input)
    input.strip == ""
  end

  def yelling?(input)
    input == input.upcase
  end

  def question?(input)
    input.end_with?("?")
  end 

end
