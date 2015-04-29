class Bob
  def hey(phrase)
    if (phrase.nil? or phrase.empty?)
      'Fine. Be that way.' 
    elsif (phrase.upcase == phrase)
      'Woah, chill out!'
    elsif (phrase[-1] == '?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
