class Bob

  def hey(message)
    message.strip! unless message.nil?
    
    return 'Fine. Be that way!' if addressed_without_saying_anything?(message)
    return 'Woah, chill out!' if yelled_at?(message)
    return 'Sure.' if asked_a_question?(message)
    return 'Whatever.' if told_something?(message)
  end

  def asked_a_question?(message)
    message =~ /\?$/
  end

  def told_something?(message)
    true
  end

  def yelled_at?(message)
    message.upcase == message
  end

  def addressed_without_saying_anything?(message)
    message.nil? || message.empty?
  end

end
