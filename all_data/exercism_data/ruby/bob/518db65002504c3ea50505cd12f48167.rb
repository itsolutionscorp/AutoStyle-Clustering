class Bob
  ALL_CAPS = /\A[^a-z]+\z/
  QUESTION = /\?\z/

  def hey(message)
    case message.to_s
    when ALL_CAPS
      "Woah, chill out!"
    when QUESTION
      "Sure."
    when ""
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
