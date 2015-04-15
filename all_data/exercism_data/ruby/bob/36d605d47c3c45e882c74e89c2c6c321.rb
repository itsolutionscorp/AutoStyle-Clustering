class Bob
  def hey(phrase)
    say = Phrase.new(phrase)
    case
    when say.nothing? then 'Fine. Be that way!'
    when say.shout? then 'Woah, chill out!'
    when say.question? then 'Sure.'
    else 'Whatever.' end
  end
end

class Phrase
  def initialize(phrase)
    @phrase = phrase.to_s.strip
  end

  def nothing?
    @phrase.empty?
  end
  def shout?
    @phrase == @phrase.upcase
  end
  def question?
    @phrase.end_with? '?'
  end
end
