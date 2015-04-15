class Bob
  def question?(str)
    str.end_with? "?"
  end

  def yell?(str)
    str =~ /\A[\W\dA-Z]+\z/ && str =~ /[A-Z]/
  end

  def say_nothing?(str)
    str =~ /\A\s*\z/
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
