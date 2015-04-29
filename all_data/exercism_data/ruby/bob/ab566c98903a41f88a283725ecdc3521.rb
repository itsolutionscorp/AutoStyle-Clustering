class Bob
  def hey(message)
    if is_silence?(message)
      'Fine. Be that way!'
    elsif is_shouty?(message)
      'Woah, chill out!'
    elsif is_question?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def is_silence?(message)
    message =~ /^\s*$/
  end

  def is_shouty?(message)
    message.upcase == message
  end

  def is_question?(message)
    message[-1] == '?'
  end
end
