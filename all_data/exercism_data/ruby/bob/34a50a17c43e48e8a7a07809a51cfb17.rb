class Bob
  def hey(sentence)
    phrase = Phrase.new(sentence)
    if phrase.empty?
      'Fine. Be that way.'
    elsif phrase.yelled?
      'Woah, chill out!'
    elsif phrase.tell?
      'Whatever.'
    else
      'Sure.'
    end
  end
end


class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def empty?
    @phrase.nil? || @phrase.empty?
  end

  def yelled?
    @phrase.upcase == @phrase
  end

  def tell?
    @phrase.end_with?('.', '!')
  end
end
