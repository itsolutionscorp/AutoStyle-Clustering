class Bob
  def hey(conversation)
    return "Fine. Be that way." if meaningless?(conversation)
    return "Sure."              if asking?(conversation)
    return "Woah, chill out!"   if shouting?(conversation)
    return "Whatever."
  end

  def shouting?(conversation)
    conversation == conversation.upcase
  end

  def meaningless?(conversation)
    conversation.nil? || conversation.empty?
  end

  def asking?(conversation)
    conversation.end_with?("?")
  end
end
