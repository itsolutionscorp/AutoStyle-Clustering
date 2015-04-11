class Message

  attr_accessor :content

  def initialize(content)
    @content = content
  end

  def reply
    if blank?
      'Fine. Be that way!'
    elsif shouting?
      'Woah, chill out!'
    elsif question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def blank?
    @content.nil? || @content.empty?
  end

  def shouting?
    @content == @content.upcase
  end

  def question?
    @content.end_with? '?'
  end

end

class Bob
  def hey(msg)
    Message.new(msg).reply
  end
end
