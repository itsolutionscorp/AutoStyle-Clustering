class Bob

  def hey(input)
    input = input.strip

    if shouting?(input)
      return "Woah, chill out!"
    end

    case input[-1]
    when '?'
      "Sure."
    when nil
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private

  def shouting?(input)
    (/[A-Za-z]+/ =~ input) && (input.upcase == input)
  end


end
