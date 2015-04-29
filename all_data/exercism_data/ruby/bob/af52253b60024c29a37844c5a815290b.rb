class Bob
  def hey(s)
    if quiet(s)
      'Fine. Be that way!'
    elsif screaming(s)
      'Woah, chill out!'
    elsif asking(s)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def asking(s)
    s[s.length-1] == '?'
  end

  def screaming(s)
    s.upcase == s
  end

  def quiet(s)
    s == '' || s == nil
  end
end
