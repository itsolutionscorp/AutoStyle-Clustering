class Bob

  def hey(input)
  case
    when saying_nothing(input)
      "Fine. Be that way!"

    when shouting(input) 
     "Woah, chill out!"

    when asking_a_question(input)
        "Sure."
      else
        "Whatever."
    end
  end

  def saying_nothing(input)
    input.strip == ''
  end

  def shouting(input)
    input == input.upcase
  end

  def asking_a_question(input)
    input.end_with?('?')
  end
end
