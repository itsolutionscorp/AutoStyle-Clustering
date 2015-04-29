class Bob
  def hey(message)
    message = message.to_s
    return 'Sure.'              if question? message
    return 'Fine. Be that way.' if message.empty?
    return 'Woah, chill out!'   if caps_lock? message
    return 'Whatever.'
  end

private

  def question?(message)
    message.end_with? '?'
  end

  def caps_lock?(message)
    message.upcase == message
  end
end
