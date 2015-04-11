class Bob
  def hey(phrase)
    @phrase = Phrase.new(phrase)
    if @phrase.empty?
      'Fine. Be that way!'
    elsif @phrase.yelling?
      'Woah, chill out!'
    elsif @phrase.question?
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
