class Bob

  def hey(input)
    if are_you_shouting_at_me?(input)
      'Woah, chill out!'
    elsif any_questions?(input)
      'Sure.'
    elsif you_said_something?(input)
      'Whatever.'
    else
      'Fine. Be that way!'
    end
  end

private
  def are_you_shouting_at_me?(str)
    str.upcase == str and str.downcase != str
  end

  def any_questions?(str)
    str.end_with?('?')
  end

  def you_said_something?(str)
    str.match(/\S/)
  end

end
