class Bob
  attr_accessor :message

  def hey(message)
    @message = message || ''
    if    silence?  then "Fine. Be that way."
    elsif shouting? then "Woah, chill out!"
    elsif question? then "Sure."
    else "Whatever." end
  end

  private
  def silence?
    message.empty?
  end

  def shouting?
    message == message.upcase
  end

  def question?
    message.end_with? '?'
  end
end
