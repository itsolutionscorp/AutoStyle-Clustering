class Bob
  def hey(message)
    @message = message.strip
    case
    when shouting? then 'Woah, chill out!'
    when question? then 'Sure.'
    when silent? then 'Fine. Be that way!'
    else 'Whatever.'
    end
  end

  private

  attr_reader :message

  def shouting?
    message =~ /[A-Z]/ && message == message.upcase
  end

  def question?
    message.end_with?('?')
  end

  def silent?
    message.empty?
  end
end
