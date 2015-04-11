class Bob

  def hey(say)
    @say = say
    silence? || yelling? || question? || statement?
  end

  private

  def silence?
    if @say.to_s.length == 0
      'Fine. Be that way!'
    end
  end

  def yelling?
    if @say.upcase == @say
      'Woah, chill out!'
    end
  end

  def question?
    if @say[-1] == '?'
      'Sure.'
    end
  end

  def statement?
    'Whatever.'
  end
end
