class Bob
  def hey(phrase)
    @phrase = Phrase.new(phrase)
    case @phrase
    when @phrase.empty?
      'Fine. Be that way!'
    when @phrase.yelling?
      'Woah, chill out!'
    when @phrase.question?
      'Sure.'
    else
      "Whatever."
    end
  end
end

class Phrase
  attr_accessor :text

  def initialize(text)
    @text = text
  end

  def yelling?
    @text.upcase!.nil?
  end

  def question?
    @text.chars.last == '?'
  end

  def empty?
    @text.rstrip.empty?
  end
end
