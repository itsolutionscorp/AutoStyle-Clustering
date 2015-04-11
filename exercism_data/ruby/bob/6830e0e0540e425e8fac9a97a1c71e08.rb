class Bob
  def hey(message)
    message = Phrase.new(message.to_s)
    if    message.silence?  then "Fine. Be that way."
    elsif message.shouting? then "Woah, chill out!"
    elsif message.question? then "Sure."
    else "Whatever." end
  end
end

class Phrase < String
  attr_accessor :message

  def initialize(message)
    @message = message
  end

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
