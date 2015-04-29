class Bob
  def hey(str)
    msg = Message.new(str)
    if msg.shout?
      "Woah, chill out!"
    elsif msg.question?
      "Sure."
    elsif msg.silence?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end

class Message
  attr_reader :str

  def initialize(str)
    @str = str
  end

  def shout?
    str.upcase == str and !silence?
  end

  def silence?
    str.gsub(/\s+/, "") == ''
  end

  def question?
    str =~ /\?\z/
  end
end
