class Message < String
  def yelling?
    self == upcase
  end

  def question?
    end_with? ??
  end
end

class Bob
  def hey message
    message = Message.new(message.to_s)
    case
    when message.empty?
      'Fine. Be that way!'
    when message.yelling?
      'Woah, chill out!'
    when message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end
