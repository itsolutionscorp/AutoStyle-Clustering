class Bob
  def hey message
    @message = message

    return "Fine. Be that way!" if empty_message?
    return "Woah, chill out!"   if all_caps?
    return "Sure."              if question?

    "Whatever."
  end

  def empty_message?
    @message.nil? || @message.empty?
  end

  def all_caps?
    @message.upcase == @message
  end

  def question?
    @message.end_with? "?"
  end
end
