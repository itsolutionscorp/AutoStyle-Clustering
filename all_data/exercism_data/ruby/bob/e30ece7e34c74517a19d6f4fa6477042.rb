class Bob
  def hey(phrase)
    if isEmpty(phrase)
      'Fine. Be that way!'
    elsif isYelling(phrase)
      'Woah, chill out!' 
    elsif isAsking(phrase)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def isEmpty(phrase)
    not phrase or phrase.eql? '' or phrase.strip!.eql? ''
  end
  def isYelling(phrase)
    phrase.upcase.eql? phrase
  end
  def isAsking(phrase)
    phrase.end_with? '?'
  end
end
