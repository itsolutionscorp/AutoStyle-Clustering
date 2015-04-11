class Bob
  def hey(message)
    case
    when (message || '') =~ %r{^\s*\z}
      'Fine. Be that way!'
    when message.upcase == message
      'Woah, chill out!'
    when message =~ %r{\?\z}
      'Sure.'
    else
      'Whatever.'
    end
  end
end
