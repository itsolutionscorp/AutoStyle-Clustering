class Bob
  
  def hey(*message)
    if message.empty? || is_empty?(message[0])
      "Fine. Be that way!"
    elsif is_yelling?(message[0])
      "Woah, chill out!"
    elsif is_a_question?(message[0])
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def is_a_question?(message)
    message && ( message.to_s.strip.end_with?('?') )
  end

  def is_empty?(message)
    message.nil? || message.to_s.strip.empty?
  end

  def is_yelling?(message)
    if message.nil?
      return false
    elsif message.to_s.upcase == message.to_s
      return true
    else
      return false
    end
  end

end
