class Bob
  def hey(message)
    @@message = message

    if silent?
      'Fine. Be that way.'
    elsif shouting?
      'Woah, chill out!' 
    elsif asking?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def message
    @@message
  end

  def asking?
    message.end_with? '?'
  end

  def shouting?
    message.upcase == message
  end

  def silent?
    message.to_s.empty?
  end
end
