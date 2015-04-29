class Bob
  def hey(s)
    phrase = Phrase.new(s)
    if phrase.silent?
      'Fine. Be that way!'
    elsif phrase.shouting?
      'Woah, chill out!'
    elsif phrase.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def silent?
    @phrase.strip.empty?
  end

  def question?
    @phrase.end_with?('?')
  end

  def shouting?
    @phrase == @phrase.upcase
  end
end
