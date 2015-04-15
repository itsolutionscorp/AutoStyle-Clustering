class Bob

  SILENCE  = /\A\s*\z/
  SHOUTING = /\A[^a-z]+\z/
  QUESTION = /\?\z/

  def hey(input)
    case input
    when SILENCE  then "Fine. Be that way!"
    when SHOUTING then "Woah, chill out!"
    when QUESTION then "Sure."
    else               "Whatever."
    end
  end

end
