class Bob
  def hey(text)
    phrase = Phrase.new(text)

    if phrase.blank?
      'Fine. Be that way.'
    elsif phrase.yelling?
      'Woah, chill out!'
    elsif phrase.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def blank?
    phrase.nil? || phrase.empty?
  end

  def question?
    phrase.end_with? '?'
  end

  def yelling?
    phrase.upcase == phrase
  end
end
