class Message < String

  def question?
    end_with? '?'
  end

  def yelled?
    upcase == self
  end

  def nothing?
    empty?
  end

end

class Bob

  def hey(message)
    message = Message.new message.to_s
    case
    when message.nothing?
      'Fine. Be that way.'
    when message.yelled?
      'Woah, chill out!'
    when message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

end
