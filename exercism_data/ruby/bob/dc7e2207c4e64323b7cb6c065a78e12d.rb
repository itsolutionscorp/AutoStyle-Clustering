class Bob

  def hey input

    passive_agressive= /^\s*$/
    question = /.*\?$/

    case input.to_s
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
