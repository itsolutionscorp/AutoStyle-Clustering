class Bob
  def hey(message)
    if detect_shout(message)
      "Whoa, chill out!"
    elsif detect_silence(message)
      "Fine. Be that way!"
    elsif detect_question(message)
      "Sure."
    else
      "Whatever."
    end
  end

  private
  def detect_shout(message)
    message.match(/[A-Z]/) && message.upcase == message
  end

  def detect_silence(message)
    message.strip.length == 0
  end

  def detect_question(message)
    message.end_with? '?'
  end
end
