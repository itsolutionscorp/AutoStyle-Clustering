class Bob
  def hey(message)
    case message
    when said_nothing?
      'Fine. Be that way.'
    when yelling?
      'Woah, chill out!'
    when asked_a_question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def yelling?
    @yelling ||= ->(message) { message == message.upcase }
  end

  def asked_a_question?
    @question ||= ->(message) { /\?\z/ =~ message }
  end

  def said_nothing?
    @nothing ||= -> (message) { message.to_s.empty? }
  end
end
