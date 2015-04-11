class Bob

  def hey(speech)
    speech = Message.new(speech)

    if speech.empty?
      "Fine. Be that way!"
    elsif speech.shouting?
      "Woah, chill out!"   
    elsif speech.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Message

  def initialize(s)
    @msg = String.new(s.strip)
  end

  def question?
    @msg.end_with?("?")
  end
  
  def shouting?
    @msg.upcase == @msg && @msg =~ /[A-z]/
  end

  def empty?
    @msg.empty?
  end

end
