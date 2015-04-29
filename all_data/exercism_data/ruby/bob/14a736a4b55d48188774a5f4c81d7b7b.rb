class Bob
  
  def hey(message=nil)
    if is_empty?(message)
      "Fine. Be that way!"
    elsif is_yelling?(message)
      "Woah, chill out!"
    elsif is_a_question?(message)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def is_a_question?(message)
    message.to_s.strip.end_with?('?')
  end

  def is_empty?(message)
    message.to_s.strip.empty?
  end

  def is_yelling?(message)
    message.to_s.upcase == message.to_s
  end

end
