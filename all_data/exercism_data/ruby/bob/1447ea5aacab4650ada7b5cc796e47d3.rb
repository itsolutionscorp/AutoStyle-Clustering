class Bob
  def hey(message)
    message = message.to_s
    case
    when is_silence?(message)
      'Fine. Be that way!'
    when is_shouting?(message)
      'Woah, chill out!'
    when is_question?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def is_silence?(message)
    message.strip == ''
  end

  def is_shouting?(message)
    message == message.upcase
  end

  def is_question?(message)
    message.end_with? '?'
  end
end
