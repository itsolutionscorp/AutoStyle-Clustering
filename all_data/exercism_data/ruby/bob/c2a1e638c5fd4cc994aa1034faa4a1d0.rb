class Bob

  def hey(message)
    if shouting?(message)
      'Woah, chill out!'
    elsif asking?(message)
      'Sure.'
    elsif silence?(message)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

private

  def shouting?(message)
    message.gsub(/[^[:alpha:]]/, '').match(/^[[:upper:]]+$/)
  end

  def asking?(message)
    message.end_with?('?')
  end

  def silence?(message)
    message.strip.empty?
  end

end
