class Bob
  attr_reader :message

  def hey(message)
    response_for(message)
  end

  private

  def response_for(message)
    @message = message.strip

    if is_forceful?
      'Woah, chill out!'
    elsif is_question?
      'Sure.'
    elsif is_empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  def is_empty?
    message.empty?
  end

  def is_forceful?
    message =~ /[A-Z]/ && message == message.upcase
  end

  def is_question?
    message.end_with?('?')
  end
end
