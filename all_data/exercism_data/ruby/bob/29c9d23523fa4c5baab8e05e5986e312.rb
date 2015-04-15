class Bob
  def hey(content)
    content = Message.new(content)

    case
      when content.nothing? then 'Fine. Be that way.'
      when content.question? then 'Sure.'
      when content.shout? then 'Woah, chill out!'
      else 'Whatever.'
    end	
  end
end

class Message

  attr_reader :content

  def initialize(content)
      @content = content.to_s
  end

  def question?
    content.end_with? "?"
  end

  def shout?
    content.upcase == content
  end

  def nothing?
    content.empty?
  end
end
