class Bob
  attr_accessor :message

  def hey(message)
    @message = message || ''
    if    silence?  then "Fine. Be that way."
    elsif shouting? then "Woah, chill out!"
    elsif question? then "Sure."
    else "Whatever." end
  end

  def silence?
    message.empty?
  end
  private :silence?

  def shouting?
    message == message.upcase
  end
  private :shouting?

  def question?
    message[-1] == '?'
  end
  private :question?
end
