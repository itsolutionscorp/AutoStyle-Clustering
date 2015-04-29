class Bob

  def hey(message)
    @message = message

    if question? && message[0..-2] == message.to_i.to_s
      'Sure.'
    elsif (question? && shouting?) || (shouting? ^ (message == message.downcase))
      'Woah, chill out!'
    elsif question?
      'Sure.'
    elsif empty_message?
      'Fine. Be that way!'
    else
      'Whatever.'
    end

  end

  private

  def question?
    @message[-1] == '?'
  end

  def empty_message?
    @message.strip == ''
  end

  def shouting?
    @message == @message.upcase
  end

end
