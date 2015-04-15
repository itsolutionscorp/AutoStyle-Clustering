class Message
  attr_reader :content

  def initialize(content)
    @content = content.to_str
  end

  def question?
    content.end_with? '?'
  end

  def shouting?
    content.upcase == content && content.downcase != content
  end

  def silent?
    content.strip.empty?
  end
end

class Bob
  def hey(message)
    case Message.new(message)
    when shouting?
      "Woah, chill out!"
    when question?
      "Sure."
    when silent?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private

  def shouting?
    ->(msg) { msg.shouting? }
  end

  def question?
    ->(msg) { msg.question? }
  end

  def silent?
    ->(msg) { msg.silent? }
  end
end
