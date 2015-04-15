class Bob
  def hey(phrase)
    prompt = Phrase.new(phrase)
    case
    when prompt.silence?
      'Fine. Be that way!'
    when prompt.yelling?
      'Woah, chill out!'
    when prompt.questioning?
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
  def yelling?
    phrase.strip.upcase == phrase
  end

  def questioning?
    phrase.strip.end_with?('?')
  end

  def silence?
    phrase.strip.empty?
  end
end
