class Bob
  def hey(raw)
    message = Message.new(raw)

    case
    when message.bogus?
      'Fine. Be that way!'
    when message.shout?
      'Woah, chill out!'
    when message.grill?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Message
  def initialize(raw)
    @raw = raw
  end

  def bogus?
    @raw.to_s.empty?
  end

  def shout?
    @raw.upcase == @raw
  end

  def grill?
    @raw.chars.last == '?'
  end
end
