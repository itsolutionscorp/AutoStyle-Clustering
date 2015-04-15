class Message
  def initialize(arg)
    @arg = arg
  end

  def is_yelling?
    (@arg == @arg.upcase) && (@arg =~ /[A-Z]/)
  end

  def is_question?
    @arg.strip.end_with?("?")
  end

  def is_silent?
    @arg.strip.empty?
  end
end


class Bob
  def hey(arg)
    message = Message.new(arg)
    if message.is_yelling?
      "Woah, chill out!"
    elsif message.is_question?
      "Sure"
    elsif message.is_silent?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
