class Bob
  def question?(str)
    str.split("\n").join("|") =~ /^[^?]+\?$/
  end

  def yell?(str)
    str =~ /^[A-Z0-9\s$&+,:;=?@#|'<>.\-\^*()%!]+$/ &&
    str =~ /^.+[A-Z]+/
  end

  def say_nothing?(str)
    str == "" || str =~ /^[\s$&+,:;=?@#|'<>.\-\^*()%!]+$/
  end

  def hey(arg)
    if yell?(arg)
      'Woah, chill out!'
    elsif question?(arg)
      'Sure.'
    elsif say_nothing?(arg)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

end
