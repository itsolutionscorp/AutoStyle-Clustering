class Bob
  def hey(message)
    string_message = message.to_s

    case
    when question?(string_message) then 'Sure.'
    when yell?(string_message) then 'Woah, chill out!'
    when empty?(string_message) then 'Fine. Be that way.'
    else 'Whatever.'
    end
  end

  def question?(message)
    message[-1] == '?'
  end

  def yell?(message)
    message.scan(/[A-Z]{2,}/).any?
  end

  def empty?(message)
    message.empty?
  end
end
