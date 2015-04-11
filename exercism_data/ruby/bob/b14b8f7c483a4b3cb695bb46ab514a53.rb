class Bob

  def hey(message)
    case true
      when is_silence?(message)  then 'Fine. Be that way!'
      when is_shouting?(message) then 'Woah, chill out!'
      when is_question?(message) then 'Sure.'
      else 'Whatever.'
    end
  end

private

  def is_shouting?(message)
    message.upcase == message && message.downcase != message
  end

  def is_question?(message)
    /\?\z/ === message
  end

  def is_silence?(message)
    message.strip == ''
  end
end
