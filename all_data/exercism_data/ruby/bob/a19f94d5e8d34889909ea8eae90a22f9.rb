class Bob

  SILENCE  = /\A\s*\z/
  YELLING  = /\A[^a-z0-9]*\z/
  FORCEFUL = /\A[^a-z]*!\z/
  QUESTION = /\?\z/

  def hey(input_to_bob)
    case String(input_to_bob)
    when SILENCE           then "Fine. Be that way!"
    when YELLING, FORCEFUL then "Woah, chill out!"
    when QUESTION          then "Sure."
    else "Whatever."
    end
  end

end
