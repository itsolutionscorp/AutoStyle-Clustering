class Bob
  def hey(message)
    respond(message)
  end

  private
  def respond(message)
    case
      when shouting?(message)
        return 'Woah, chill out!'
      when asking?(message)
        return 'Sure.'
      when silent?(message)
        return 'Fine. Be that way.'
      else
        'Whatever.'
    end
  end

  def shouting?(message)
    present?(message) && message == message.upcase
  end

  def asking?(message)
    present?(message) && message.split('').last == '?'
  end

  def exclaiming?(message)
    present?(message) && message.split('').last == '!'
  end

  def silent?(message)
    ! present?(message)
  end

  def present?(message)
    message && message != ''
  end
end
