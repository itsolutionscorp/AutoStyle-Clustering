class Bob
  def hey(phrase)
    say = Phrase.new(phrase.strip)
    case
    when say.nothing? then 'Fine. Be that way!'
    when say.it_loud? then 'Woah, chill out!'
    when say.question? then 'Sure.'
    else 'Whatever.' end
  end
end

class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def nothing?
    @phrase.empty?
  end
  def it_loud?
    @phrase == @phrase.upcase
  end
  def question?
    @phrase.end_with? '?'
  end
end
