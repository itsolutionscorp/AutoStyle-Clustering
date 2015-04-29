class Bob
  def hey(conversation)
    return "Fine. Be that way." if conversation.nil? || conversation.empty?
    return "Sure."              if conversation.end_with?("?")
    return "Woah, chill out!"   if shouting?(conversation)
    return "Whatever."
  end

  def shouting?(conversation)
    conversation == conversation.upcase
  end
end
