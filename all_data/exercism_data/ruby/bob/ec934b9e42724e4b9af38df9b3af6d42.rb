class Bob
  def hey(phrase)
    case
    when empty?(phrase)
      'Fine. Be that way!'
    when yelling?(phrase)
      'Woah, chill out!'
    when question?(phrase)
      'Sure.'
    else
      'Whatever'
    end
  end

  def empty?(phrase)
    phrase.strip.empty?
  end

  def yelling?(phrase)
    phrase == phrase.upcase
  end

  def question?(phrase)
    phrase.end_with?("?")
  end
end
