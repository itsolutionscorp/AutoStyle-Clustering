class Bob
  def hey(conversation_opener)
    text = Text.new(conversation_opener)

    case
      when text.empty? then 'Fine. Be that way.'
      when text.shouting? then 'Woah, chill out!'
      when text.question? then 'Sure.'
      else 'Whatever.'
    end
  end
end

class Text
  def initialize(text)
    @text = text
  end

  def empty?
    @text.nil? || @text.empty?
  end

  def shouting?
    @text.upcase == @text
  end

  def question?
    @text.end_with?('?')
  end
end
