class Message

  attr_reader :content

  def initialize(content)
    @content = String(content)
  end

  def nothing?
    content.empty?
  end

  def shouting?
    content == content.upcase
  end

  def question?
    content.end_with? '?'
  end

end

class Bob

  attr_reader :hears

  def hey(msg)
    @hears = Message.new(msg)
    reply
  end

  def reply
    case true
    when hears.nothing?
      'Fine. Be that way!'
    when hears.shouting?
      'Woah, chill out!'
    when hears.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

end
