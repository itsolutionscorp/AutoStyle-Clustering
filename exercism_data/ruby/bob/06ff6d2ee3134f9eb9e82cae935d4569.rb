class Bob
  def hey(message)
    @message = message
    silence || shouting || question || "Whatever."
  end

private

  def silence
    return nil unless @message.strip.empty?
    "Fine. Be that way!"
  end

  def shouting
    return nil unless @message.upcase == @message
    "Woah, chill out!"
  end

  def question
    return nil unless @message.end_with?("?")
    "Sure."
  end
end
