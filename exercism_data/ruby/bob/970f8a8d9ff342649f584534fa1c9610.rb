class Bob
  def hey(message)
    if is_a_question?(message)
      'Sure.'
    elsif is_yelling?(message)
      'Woah, chill out!'
    elsif is_not_a_statement?(message)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

  def is_a_question?(message)
    return false if is_yelling?(message)
    message.end_with? '?'
  end

  def is_yelling?(message)
    return false if is_not_a_statement?(message)
    if message == message.upcase && message.match(/[A-Z]/)
      true
    else
      false
    end
  end

  def is_not_a_statement?(message)
    if message.strip.empty? || message.nil?
      true
    else
      false
    end
  end
end
