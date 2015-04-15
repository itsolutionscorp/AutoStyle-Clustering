class Bob

  def hey(message)
    @message = message

    case
      when empty_message?
        'Fine. Be that way!'
      when shouting?
        'Woah, chill out!'
      when question?
        'Sure.'
      else
        'Whatever.'
    end

  end

  private

  def question?
    @message.end_with?('?')
  end

  def empty_message?
    @message.strip.empty?
  end

  def shouting?
    @message =~ /[A-Z]/ && @message.upcase == @message
  end

end
