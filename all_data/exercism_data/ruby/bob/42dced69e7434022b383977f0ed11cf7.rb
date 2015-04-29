class Bob

  # A message can be `silent`, `yelled`, or `questioning`;
  # See reasoning behind this naming scheme here:
  # http://exercism.io/user/submissions/51eebb2138e08870020000a2
  # (Feedback much appreciated!)
  def hey(message)
    if silent? message
      'Fine. Be that way.'
    elsif yelled? message
      'Woah, chill out!'
    elsif questioning? message
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silent?(message)
    message.nil? || message.empty?
  end

  def yelled?(message)
    message.upcase == message
  end

  def questioning?(message)
    message.end_with? '?'
  end

end
