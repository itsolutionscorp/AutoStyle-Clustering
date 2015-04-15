class Bob
  def hey(phrase)
    respond_to phrase
  end

private
  def respond_to(phrase)
    if silent?(phrase)
      'Fine. Be that way!'
    elsif shouted?(phrase)
      'Woah, chill out!'
    elsif question?(phrase)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def silent?(phrase)
    phrase.nil? || phrase.strip.empty?
  end

  def shouted?(phrase)
    !(phrase =~ /[[:lower:]]/)
  end

  def question?(phrase)
    phrase =~ /.*\?$/
  end
end
