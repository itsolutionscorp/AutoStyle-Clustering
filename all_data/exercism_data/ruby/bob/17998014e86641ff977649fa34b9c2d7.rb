class Bob
  def hey(message)
    if silence?(message)
      'Fine. Be that way!'
    elsif shouting?(message)
      'Woah, chill out!'
    elsif question?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def shouting?(message)
    message =~ /^[^a-z]*$/
  end

  def question?(message)
    message =~ /.*\?$/
  end

  def silence?(message)
    message.nil? || message.strip.empty?
  end
end
