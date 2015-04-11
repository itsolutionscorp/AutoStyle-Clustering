class Bob
  attr_accessor :message

  def hey(message)
    self.message = message

    case
    when empty?
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

  def empty?
    message.strip.empty?
  end

  def shouting?
    message.upcase.eql?(message)
  end

  def question?
    message.end_with?('?')
  end
end
