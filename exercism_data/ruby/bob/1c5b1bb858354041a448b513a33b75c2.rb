class Bob
  def hey(talk)
    if yelling?(talk)
      'Woah, chill out!'
    elsif question?(talk)
      'Sure.'
    elsif silence?(talk)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

  def yelling?(talk)
    talk.upcase == talk && talk =~ /[[:alpha:]]/
  end

  def question?(talk)
    talk.end_with?('?')
  end

  def silence?(talk)
    talk.strip.empty?
  end
end
