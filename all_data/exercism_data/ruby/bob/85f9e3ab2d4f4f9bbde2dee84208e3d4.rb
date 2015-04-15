class Bob

  def hey(message)
    case
    when ignored?(message)
      'Fine. Be that way!'
    when yelled_at?(message)
      'Woah, chill out!'
    when asked?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def asked?(message)
    message.end_with?('?')
  end

  def yelled_at?(message)
    message == message.upcase
  end

  def ignored?(message)
    message.to_s == ''
  end
end
