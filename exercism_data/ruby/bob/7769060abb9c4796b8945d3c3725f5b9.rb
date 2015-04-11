class Bob

  def question?(s)
    /\? *\z/ =~ s
  end

  def yelling?(s)
    /[A-Z]/ =~ s and s.upcase == s
  end

  def nothing?(s)
    /\A *\z$/ =~ s
  end

  def hey(s)
    if yelling?(s)
      'Woah, chill out!'
    elsif question?(s)
      'Sure.'
    elsif nothing?(s)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

end
