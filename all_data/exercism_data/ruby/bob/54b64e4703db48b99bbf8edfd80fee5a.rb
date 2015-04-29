class Bob
  def hey message
    @message = message || ""

    return "Fine. Be that way!" if responding_to_silence?
    return "Woah, chill out!"   if responding_to_shout?
    return "Sure."              if responding_to_question?

    "Whatever."
  end

  private
  def responding_to_silence?
    @message.empty?
  end

  def responding_to_shout?
    @message.upcase == @message
  end

  def responding_to_question?
    @message.end_with? "?"
  end
end
