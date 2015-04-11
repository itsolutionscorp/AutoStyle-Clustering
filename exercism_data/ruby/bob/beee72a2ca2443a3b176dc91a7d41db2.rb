class Bob
  def question(s)
    s.end_with?('?')
  end

  def empty(s)
    s.strip.empty?
  end

  def shouting(s)
    s == s.upcase
  end

  def hey(message)
    if empty(message)
      'Fine. Be that way!'
    elsif shouting(message)
      'Woah, chill out!'
    elsif question(message)
      'Sure.'
    else
      'Whatever.'
    end
  end
end
