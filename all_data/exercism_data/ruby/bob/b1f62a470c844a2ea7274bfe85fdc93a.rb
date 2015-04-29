class Message < String
  def initialize(string)
    super
    gsub!(/\s+/, ' ')
  end

  def yelled?
    self == upcase && match(/[A-Z]/)
  end

  def question?
    match(/\?$/)
  end

  def silence?
    strip.empty?
  end
end

class Bob
  def hey(string)
    message = Message.new(string)

    case
    when message.yelled? then "Woah, chill out!"
    when message.question? then "Sure."
    when message.silence? then "Fine. Be that way!"
    else "Whatever."
    end
  end
end
