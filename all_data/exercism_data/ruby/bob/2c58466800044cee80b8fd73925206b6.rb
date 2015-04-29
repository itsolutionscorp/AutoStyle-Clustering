class Bob
  def hey(drivel)
    if giving_me_the_silent_treatment?(drivel)
      'Fine. Be that way.'
    elsif asking_me_a_question?(drivel)
      'Sure.'
    elsif yelling_at_me?(drivel)
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  def yelling_at_me?(drivel)
    !drivel.match(/[a-z]/)
  end

  def asking_me_a_question?(drivel)
    drivel.end_with? '?'
  end

  def giving_me_the_silent_treatment?(drivel)
    drivel.empty?
  end
end
