class Bob
  def hey(conversation)
    conversation = conversation.to_s.strip

    return 'Fine. Be that way.' if quiet?(conversation)
    return "Woah, chill out!"   if shouting?(conversation)
    return "Sure."              if asking?(conversation)
    return "Whatever."
  end

  private

  def shouting?(conversation)
    conversation.upcase == conversation
  end

  def asking?(conversation)
    conversation.end_with?("?")
  end

  def quiet?(conversation)
    conversation.empty?
  end
end
