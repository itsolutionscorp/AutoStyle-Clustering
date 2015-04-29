class Bob

  def hey(say)
    if shouting?(say)
      'Woah, chill out!'
    elsif asking_a_question?(say)
      'Sure.'
    elsif not_saying_anything?(say)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

  def shouting?(say)
    !say.strip.empty? && say == say.upcase
  end

  def asking_a_question?(say)
    say.end_with?('?')
  end

  def not_saying_anything?(say)
    say.strip.empty?
  end

end
