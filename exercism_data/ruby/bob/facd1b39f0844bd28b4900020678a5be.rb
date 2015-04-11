class Bob
  def hey(message)
    @message = message.strip
    respond
  end

  private

  attr_reader :message

  def respond
    case
    when shouting? then 'Woah, chill out!'
    when question? then 'Sure.'
    when message.empty? then 'Fine. Be that way!'
    else 'Whatever.'
    end
  end

  def shouting?
    message =~ /[A-Z]/ && message == message.upcase
  end

  def question?
    message.end_with?('?')
  end
end
