class Bob

  def hey input

    passive_agressive= /^\s*$/
    question = /.*\?$/
    input = input.to_s

    case input
    when passive_agressive
      "Fine. Be that way!"
    when input.upcase
      "Woah, chill out!"
    when question
      "Sure."
    else
      "Whatever."
    end
  end

end
