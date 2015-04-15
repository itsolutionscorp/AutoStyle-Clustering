class Message
  def initialize(content)
    @content = content
  end

  def question?
    @content.end_with? '?'
  end

  def shouting?
    @content.upcase == @content && @content.downcase != @content
  end

  def silent?
    @content.strip.empty?
  end
end

class Bob
  def hey(message)
    case Message.new(message)
    when ->(msg) { msg.shouting? }
      "Woah, chill out!"
    when ->(msg) { msg.question? }
      "Sure."
    when ->(msg) { msg.silent? }
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
