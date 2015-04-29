class Message
  def initialize(content)
    @content = content.gsub(/ /, '')
  end

  def question?
    @content.end_with? '?'
  end

  def all_caps?
    @content.upcase == @content && @content.downcase != @content
  end

  def silent?
    @content.empty?
  end
end

class Bob
  def hey(message)
    message = Message.new(message)
    if message.all_caps?
      "Woah, chill out!"
    elsif message.question?
      "Sure."
    elsif message.silent?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
