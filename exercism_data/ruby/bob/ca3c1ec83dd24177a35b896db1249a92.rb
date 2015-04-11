class Bob
  def hey(message)
    case
    when say_nothing?(message)   then "Fine. Be that way."
    when ask_question?(message)  then "Sure."
    when yell_at_him?(message)   then "Woah, chill out!"
    else "Whatever."
    end
  end

  def say_nothing?(message)
    message.nil? || message.empty?
  end

  def ask_question?(message)
    message.end_with?('?')
  end

  def yell_at_him?(message)
    message == message.upcase
  end
end
