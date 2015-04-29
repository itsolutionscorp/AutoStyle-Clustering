class Bob
  def initialize
  end

  def hey(phrase)
    @phrase = phrase
    if silence?
      'Fine. Be that way!'
    elsif shouting?
      'Woah, chill out!'
    elsif question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  def shouting?
    @phrase == @phrase.upcase
  end

  def question?
    @phrase[-1] == '?'
  end

  def silence?
    @phrase == nil || @phrase.match(/\w/) == nil
  end
end
