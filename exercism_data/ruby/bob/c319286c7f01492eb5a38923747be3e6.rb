class Bob
  def hey(message)
    phrase = Phrase.new(message)
    case
    when phrase.is_silence? then 'Fine. Be that way!'
    when phrase.is_shout? then 'Woah, chill out!'
    when phrase.is_question? then 'Sure.'
    else 'Whatever.' end
  end
end

class Phrase
  def initialize(phrase)
    @phrase = phrase.to_s.strip
  end

  def is_silence?
    @phrase.empty?
  end
  def is_shout?
    @phrase == @phrase.upcase
  end
  def is_question?
    @phrase.end_with? '?'
  end
end
