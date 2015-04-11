class Bob
  def hey(phrase)
    phrase = Phrase.new({text: phrase})
    case
    when phrase.empty?
      'Fine. Be that way!'
    when phrase.yelling?
      'Woah, chill out!'
    when phrase.question?
      'Sure.'
    else
      "Whatever."
    end
  end
end

class Phrase
  attr_accessor :text

  def initialize(args)
    @text = args[:text].to_s
  end

  def yelling?
    text.upcase == @text
  end

  def question?
    text.chars.last == '?'
  end

  def empty?
    text.rstrip.empty?
  end
end
