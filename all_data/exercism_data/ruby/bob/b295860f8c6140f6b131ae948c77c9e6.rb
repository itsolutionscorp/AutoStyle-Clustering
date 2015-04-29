class Bob
  def hey(msg)
    m = Message.new(msg)

    if m.silent?
      "Fine. Be that way!"
    elsif m.questioning?
      "Sure."
    elsif m.yelling?
      "Woah, chill out!"
    else
      "Whatever."
    end
  end
end

class Message
  attr_reader :msg

  def initialize(msg)
    @msg = String(msg).gsub(/[\r\n]/, ' ')
  end

  def silent?
    msg =~ /^\s*$/
  end

  def questioning?
    msg.end_with?('?') && !yelling?
  end

  def yelling?
    msg.upcase == msg && msg =~ /[a-zA-Z]+/
  end
end
