class Bob
  def hey(input)
    response = ""

    case
    when silence?(input)
      response = "Fine. Be that way."
    when shouting?(input)
      response = "Woah, chill out!"
    when question?(input)
      response = "Sure."
    else
      response = "Whatever."
    end

    response
  end

  def silence?(input)
    !input or input.empty?
  end

  def shouting?(input)
    input == input.upcase
  end

  def question?(input)
    input.end_with?('?')
  end
end
