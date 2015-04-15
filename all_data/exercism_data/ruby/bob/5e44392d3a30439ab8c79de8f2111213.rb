class Bob

  def hey(input)
    case
    when input.strip == ""
      silence
    when input == input.upcase
      yelling
    when input.end_with?("?")
      question
    else
      other
    end
  end

  def silence
    "Fine. Be that way!"
  end

  def yelling
    "Woah, chill out!"
  end

  def question
    "Sure."
  end 

  def other
    "Whatever."
  end

end
