class Bob
  def hey(input)
    message = Message.new(input)
    if message.silent?
      'Fine. Be that way!'
    elsif message.shout?
      'Woah, chill out!'
    elsif message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Message
  attr_reader :content

  def initialize(content)
    @content = content.to_s.strip
  end

  def silent?
    content.empty?
  end

  def shout?
    content.upcase == content
  end

  def question?
    content.end_with?('?')
  end
end
