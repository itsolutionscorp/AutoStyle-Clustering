class Bob

  def hey(input)

    @input = input

    case
    when silence?
      "Fine. Be that way!"
    when yelling?
      "Woah, chill out!"
    when question?
      "Sure."
    else
      "Whatever."
    end
  end

  def silence?
    @input.strip == ""
  end

  def yelling?
    @input == @input.upcase
  end

  def question?
    @input.end_with?("?")
  end 

end
