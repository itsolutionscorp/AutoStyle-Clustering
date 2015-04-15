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

  private

  def forcefull?
    @phrase == @phrase.upcase
  end

  def question?
    @phrase.end_with? '?'
  end

  def silence?
    @phrase.to_s.strip == ''
  end
end
