class Bob
  def hey(challenge)
    if silence?(challenge)
      'Fine. Be that way!'
    elsif shouting?(challenge)
      'Woah, chill out!'
    elsif question?(challenge)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def question?(challenge)
    challenge.end_with?('?')
  end

  def shouting?(challenge)
    !/.*[[:lower:]].*/.match(challenge) && /.*[[:upper:]].*/.match(challenge)
  end

  def silence?(challenge)
    challenge.strip.empty?
  end
end
