class Bob
  ALL_CAPS = /\A[^a-z]+\z/
  QUESTION = /\?\z/
  NOTHING  = ""

  def hey(message)
    case message.to_s
    when ALL_CAPS then "Woah, chill out!"
    when QUESTION then "Sure."
    when NOTHING  then "Fine. Be that way!"
    else               "Whatever."
    end
  end
end
