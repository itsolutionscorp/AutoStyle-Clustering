class Bob
  def hey(message)

    question = message[-1] == "?"
    yelling  = message.upcase == message
    silence  = !message.match(/\w+/)
    numeric  = message.match(/^\d+.*\d+$/)
    text     = !message.match(/^\d/)
    delay    = message[1] == "\n"


    if question
      if (yelling && text) || delay
        "Woah, chill out!"
      else
        "Sure."
      end
    elsif silence
      "Fine. Be that way!"
    elsif yelling && !numeric
      "Woah, chill out!"
    else
      "Whatever."
    end
  end
end
