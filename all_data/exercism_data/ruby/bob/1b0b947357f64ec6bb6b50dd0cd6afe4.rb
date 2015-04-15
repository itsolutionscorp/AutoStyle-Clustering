class Bob
  def hey(message)
    message = message.strip

    if yell?(message)
      'Woah, chill out!'
    elsif question?(message)
      'Sure.'
    elsif nothing?(message)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

  def yell?(message)
    message.scan(/[a-zA-Z]/).join.match(/^[A-Z]+$/)
  end

  def question?(message)
    message[-1] == '?'
  end

  def nothing?(message)
    message.length == 0
  end
end
