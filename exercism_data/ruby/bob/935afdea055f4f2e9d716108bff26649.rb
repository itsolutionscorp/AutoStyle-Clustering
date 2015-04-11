class Bob
  def hey(phrase)
    if empty?(phrase)
      'Fine. Be that way!'
    elsif yelling?(phrase)
      'Woah, chill out!' 
    elsif asking?(phrase)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def empty?(phrase)
    not phrase or phrase.eql? '' or phrase.strip!.eql? ''
  end
  def yelling?(phrase)
    phrase.upcase.eql? phrase
  end
  def asking?(phrase)
    phrase.end_with? '?'
  end
end
