class Bob
  def hey(message)
    case
    when shouting?(message) then 'Woah, chill out!'
    when question?(message) then 'Sure.'
    when silent?(message) then 'Fine. Be that way!'
    else 'Whatever.'
    end
  end

  private

  def shouting?(message)
    message =~ /[A-Z]/ && message == message.upcase
  end

  def question?(message)
    message.end_with?('?')
  end

  def silent?(message)
    message.strip.empty?
  end
end
