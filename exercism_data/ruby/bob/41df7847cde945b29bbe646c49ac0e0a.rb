class Bob
  def hey message
    case 
    when message.blank?
      'Fine. Be that way!'
    when message.yelling?
      'Woah, chill out!'
    when message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def blank? message
    message.strip.empty?
  end

  def yelling? message
    message == message.upcase && message != message.downcase
  end

  def question? message
    message.end_with? '?'
  end
end
