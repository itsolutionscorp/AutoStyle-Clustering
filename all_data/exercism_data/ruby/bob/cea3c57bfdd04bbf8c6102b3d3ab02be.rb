class Bob
  def hey message
    if is_silence?(message)
      "Fine. Be that way."
    elsif is_shouting?(message)
      "Woah, chill out!"
    elsif is_question?(message)
      "Sure."
    else
      "Whatever."
    end
  end

  def is_shouting?(message)
    message == message.upcase
  end

  def is_question?(message)
    message.end_with?("?")
  end

  def is_silence?(message)
    message.nil? || message.empty?
  end
end
