class Bob
  def hey(received_message)
    return 'Woah, chill out!' if yelling?(received_message)
    return 'Sure.' if asking?(received_message)
    return 'Fine. Be that way!' if silence?(received_message)
    'Whatever.'
  end

  def asking?(received_message)
    received_message.end_with? '?'
  end

  def yelling?(received_message)
    received_message == received_message.upcase &&
      received_message.match(/[A-Z]/)
  end

  def silence?(received_message)
    received_message.strip == ''
  end
end
