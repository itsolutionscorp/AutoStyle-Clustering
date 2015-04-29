class Bob
  def hey(message)
    message = message.to_s
    case 
      when is_empty?(message) then 'Fine. Be that way!'
      when shouting?(message) then 'Woah, chill out!'
      when asking?(message) then 'Sure.' 
      else "Whatever."
    end
  end

  private
  def is_empty?(message)
    message.empty?
  end

  def shouting?(message)
    message == message.upcase
  end

  def asking?(message)
    message.end_with? '?'
  end
end
