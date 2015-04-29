class Bob
  def initialize
  end

  def hey(s)
    if spaces?(s)
      ignore
    elsif yelling?(s)
      yell
    elsif question?(s)
      answer
    else no_letters?(s)
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
    return true
  end

  def no_letters?(s)
    if s.match(/[a-zA-Z]+/).nil?
      return true
    else
      return false
    end
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
    return true
  end

  def question?(c)
    if c.rindex('?') != c.length - 1
      return false
    else
      return true
    end
  end

  def respond
    return 'Whatever.'
  end

  def ignore
    return 'Fine. Be that way!'
  end

  def answer
    return "Sure."
  end

  def yell
    return 'Woah, chill out!'
  end

end
