class Bob
  def hey(message)
    case
    when silence?(message) then 'Fine. Be that way!'
    when yelling?(message) then 'Woah, chill out!'
    when question?(message) then 'Sure.'
    else 'Whatever.'
    end
  end

private

  def silence?(message)
    message.nil? || message.empty?
  end

  def yelling?(message)
    message == message.upcase
  end

  def question?(message)
    message.end_with?('?')
  end
end
