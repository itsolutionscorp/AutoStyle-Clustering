class Bob
  def hey str
    phrase = Phrase.new str

    if phrase.silence?
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
  attr_reader :phrase
  def initialize str
    @phrase = str
  end

  def silence?
    phrase == '' || phrase == nil || phrase == '    '
  end

  def shouting?
    phrase == phrase.upcase
  end

  def question?
    punctuation == '?'
  end

  def punctuation
    phrase[-1]
  end
end
