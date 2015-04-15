class Bob
  SHOUT    = /\A[^a-z]+\z/
  QUESTION = /\?\z/
  SILENCE  = ""

  def hey(message)
    case message.to_s
    when SHOUT    then "Woah, chill out!"
    when QUESTION then "Sure."
    when SILENCE  then "Fine. Be that way!"
    else               "Whatever."
    end
  end
end
