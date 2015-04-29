class Bob
  def hey(message)
    respond(message)
  end

  private
  def respond(message)
    case
      when shouting?(message)
        'Woah, chill out!'
      when asking?(message)
        'Sure.'
      when silent?(message)
        'Fine. Be that way.'
      else
        'Whatever.'
    end
  end

  def shouting?(message)
    present?(message) && message == message.upcase
  end

  def asking?(message)
    present?(message) && message.end_with?('?')
  end

  def silent?(message)
    ! present?(message)
  end

  def present?(message)
    message && message != ''
  end
end
