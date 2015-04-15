class Bob

  def hey(say)
    if not_saying_anything?(say)
      'Fine. Be that way!'
    elsif shouting?(say)
      'Woah, chill out!'
    elsif asking_a_question?(say)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def not_saying_anything?(say)
    say.strip.empty?
  end

  def shouting?(say)
    say == say.upcase
  end

  def asking_a_question?(say)
    say.end_with?('?')
  end

end
