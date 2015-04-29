class Bob
  def hey(message)
    case
     when nothing?(message)
      'Fine. Be that way!'
     when yelling?(message)
      'Woah, chill out!'
     when question?(message)
      'Sure.'
     else
      'Whatever.'
    end
  end

private
  def question?(message)
    message.end_with? '?'
  end

  def yelling?(message)
    message == message.upcase
  end

  def nothing?(message)
    message.nil? || message.strip.empty?
  end
end
