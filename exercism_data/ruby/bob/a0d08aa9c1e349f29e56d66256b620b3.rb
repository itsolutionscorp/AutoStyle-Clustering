class Bob
  
  def hey(s)
    if spaces?(s)
      ignore
    elsif yelling?(s)
      yell
    elsif question?(s)
      answer
    else
      respond
    end
  end

  private

  def yelling?(c)
    if no_letters?(c)
      return false
    end
    c.each_byte do |b|
      if b >= 97 && b <= 122
        return false
      end
    end
    true
  end

  def no_letters?(s)
    s.match(/[a-zA-Z]+/).nil?
  end

  def spaces?(c)
    if c.empty?
      return true
    end

    c.each_byte do |b|
      if b != 32
        return false
      end
    end
    true
  end

  def question?(c)
    c.rindex('?') == c.length - 1
  end

  def respond
    'Whatever.'
  end

  def ignore
    'Fine. Be that way!'
  end

  def answer
    "Sure."
  end

  def yell
    'Woah, chill out!'
  end

end
