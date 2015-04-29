class Message
  def initialize(contents)
    @contents = contents
  end

  def silence?
    @contents.strip.size == 0
  end

  def question?
    @contents.end_with? "?"
  end

  def shouting?
    @contents.upcase == @contents && @contents =~ /[a-zA-Z]/
  end
end

class Bob
  def hey(message)
    m = Message.new message

    if m.shouting?
      'Woah, chill out!'
    elsif m.question?
      'Sure.'
    elsif m.silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
