class Bob
  def hey(string)
    phrase = string.to_s.strip
    case
    when shouting(phrase)
      'Woah, chill out!'
    when question(phrase)
      'Sure.'
    when silent(phrase)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

private

  def shouting(phrase)
    phrase.match(/[A-Z,0-9]+(?:\s+[A-Z-]+)+\b/)
  end

  def question(phrase)
    phrase.end_with?('?')
  end

  def silent(phrase)
   phrase.empty?
  end

end
