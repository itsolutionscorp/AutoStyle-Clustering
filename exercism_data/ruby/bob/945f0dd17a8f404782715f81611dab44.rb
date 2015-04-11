class Bob
  def hey(s)
    if silence(s)
      'Fine. Be that way!'
    elsif all_caps(s)
      'Woah, chill out!'
    elsif question(s)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def silence(s)
    s.strip.empty?
  end

  def all_caps(s)
    s.upcase === s
  end

  def question(s)
    s.end_with?('?')
  end
end
