class Bob
  def hey(string)
    phrase = string.to_s.strip
    case
    when phrase.match(/[A-Z,0-9]+(?:\s+[A-Z-]+)+\b/)
      'Woah, chill out!'
    when question
      'Sure.'
    when phrase.strip.empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

private

  def question
    phrase.end_with?('?')
  end

end
