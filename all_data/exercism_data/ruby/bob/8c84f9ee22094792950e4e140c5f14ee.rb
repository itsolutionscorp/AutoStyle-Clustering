class Bob
  def hey(phrase)
    @phrase = phrase.strip
    case
    when silence? then 'Fine. Be that way!'
    when shouting? then 'Woah, chill out!'
    when question? then 'Sure.'
    else 'Whatever.' end
  end

  def silence?
    @phrase.empty?
  end
  def shouting?
    @phrase == @phrase.upcase
  end
  def question?
    @phrase.end_with? '?'
  end
end
