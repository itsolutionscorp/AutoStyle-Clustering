class Bob
  def hey(sentence)
    if shouting?(sentence)
      'Woah, chill out!'
    elsif question?(sentence)
      'Sure.'
    elsif something?(sentence)
      'Whatever.'
    else
      'Fine. Be that way!'
    end
  end

  private

  def shouting?(sentence)
    sentence.match(/[A-Z]/) and not sentence.match(/[a-z]/)
  end

  def question?(sentence)
    sentence.end_with?('?')
  end

  def something?(sentence)
    sentence.match(/\S/)
  end
end
