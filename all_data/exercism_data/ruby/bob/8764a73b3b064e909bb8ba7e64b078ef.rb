class Bob
  def hey(message)
    if is_nothing?(message) 
      "Fine. Be that way!"
    elsif is_a_yell?(message)
      "Woah, chill out!"
    elsif is_a_question?(message)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def is_nothing?(message)
    message.strip.empty?
  end

  def is_a_question?(message)
    message.end_with?("?")
  end

  def is_a_yell?(message)
    message == message.upcase
  end
end
