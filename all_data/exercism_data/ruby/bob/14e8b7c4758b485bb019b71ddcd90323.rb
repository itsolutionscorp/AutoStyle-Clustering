class Bob

  def hey(phrase)
    case phrase
    when *empty?
      'Fine. Be that way.'
    when shouting?
      'Woah, chill out!'
    when question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def empty?
    ['', nil]
  end

  def shouting?
    /^[^a-z]*$/
  end

  def question?
    /\?$/
  end
end
