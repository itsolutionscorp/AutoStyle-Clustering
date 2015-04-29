class Bob
  def hey phrase
    @phrase = phrase

    if silence?
      'Fine. Be that way!'
    elsif forcefull?
      'Woah, chill out!'
    elsif question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  def forcefull?
    @phrase == @phrase.upcase
  end

  def question?
    @phrase[-1] == '?'
  end
  
  def silence?
    @phrase.nil? || @phrase.strip == '' 
  end
end
