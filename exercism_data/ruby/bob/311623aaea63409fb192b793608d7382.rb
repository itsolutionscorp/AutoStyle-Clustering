class Bob
  def hey(msg)
    case
    when is_silent_msg?(msg)
      'Fine. Be that way!'
    when is_yelling_at_you?(msg) # yelling at you
      'Woah, chill out!'
    when is_question?(msg)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def is_silent_msg?(msg)
    msg.strip == ''
  end

  def is_yelling_at_you?(msg)
    /[A-Za-z]/.match(msg) && msg.upcase == msg
  end

  def is_question?(msg)
    msg.end_with?('?')
  end
end
