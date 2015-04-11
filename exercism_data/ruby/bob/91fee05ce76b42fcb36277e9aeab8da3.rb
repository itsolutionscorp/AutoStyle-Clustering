class Bob
  def hey(message)
    return "Fine. Be that way!" if awkward_pause? message
    return "Woah, chill out!"   if shouting? message
    return "Sure."              if question? message
    return "Whatever."
  end

  private
  def awkward_pause?(message)
    message.strip.empty?
  end

  def shouting?(message)
    message !~ /[a-z]+/ && message =~ /[A-Z]+/
  end
  
  def question?(message)
    message.end_with?("?")
  end
end
