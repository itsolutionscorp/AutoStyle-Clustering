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
    @text = args[:text].strip
  end

  def yelling?
    text.upcase == text
  end

  def question?
    text.end_with? '?'
  end

  def empty?
    text.empty?
  end
end
