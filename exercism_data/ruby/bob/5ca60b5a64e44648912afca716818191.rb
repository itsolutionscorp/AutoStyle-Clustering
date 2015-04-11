class Bob


  def hey(message)
    case
    when shouting?(message)
      "Woah, chill out!"
    when question?(message)
      "Sure."
    when silence?(message)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private 

  def with_words(message)
    message =~ /[A-Z|a-z]/
  end

  def shouting?(message)
    if with_words(message)
      message.upcase == message
    end
  end

  def question?(message)
    message.end_with?("?")
  end

  def silence?(message)
    message.strip.empty?
  end
end
