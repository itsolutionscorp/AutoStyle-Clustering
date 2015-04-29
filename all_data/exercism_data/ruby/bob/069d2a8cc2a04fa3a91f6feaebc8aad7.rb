class Bob
  def hey(phrase)
    if !actual_saying?(phrase)
      'Fine. Be that way!'
    elsif loud?(phrase)
      'Woah, chill out!'
    elsif question?(phrase)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def actual_saying?(phrase)
    phrase.match(/\w/)
  end

  def loud?(phrase)
    phrase.match(/[A-Z]/) && !phrase.match(/[a-z]/)
  end

  def question?(phrase)
    phrase.strip.end_with?('?')
  end
end
