class Bob
  def isYelling(msg)
    msg.upcase == msg
  end

  def isQuestion(msg)
    msg.match /\?$/
  end

  def hey(msg)
    msg = '' if msg.nil?

    if msg == ''
      'Fine. Be that way.'
    elsif isYelling(msg)
      'Woah, chill out!'
    elsif isQuestion(msg)
      'Sure.'
    else
      'Whatever.'
    end
  end
end
