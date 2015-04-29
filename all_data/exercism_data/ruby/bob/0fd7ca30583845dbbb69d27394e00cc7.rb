class Bob

  SILENCE       = /\A\s*\z/
  SHOUTING_CASE = /\A[^a-z0-9]*\z/
  SHOUTING_BANG = /\A[^a-z]*!\z/
  QUESTION      = /\?\z/

  def hey(input_to_bob)
    case String(input_to_bob)
    when SILENCE        then "Fine. Be that way!"

    when SHOUTING_CASE,
         SHOUTING_BANG  then "Woah, chill out!"

    when QUESTION       then "Sure."

    else "Whatever."
    end
  end

end
