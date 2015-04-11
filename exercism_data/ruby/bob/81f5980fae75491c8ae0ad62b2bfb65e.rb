class Bob
  def hey(input)
    if silence?(input)
      "Fine. Be that way!"
    elsif asking?(input)
      "Sure."
    elsif shouting?(input)
      "Woah, chill out!"
    else
      "Whatever."
    end
  end

  def silence?(input)
    input.strip == ""
  end

  def shouting?(input)
    input.match(/\p{Lu}/m) && !input.match(/\p{Ll}/m)
  end

  def asking?(input)
    input.match(/\?\Z/m) && !shouting?(input)
  end
end
