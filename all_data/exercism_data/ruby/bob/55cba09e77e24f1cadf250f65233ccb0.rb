class Bob

  def hey(txt)
    case
    when saying_nothing(txt)
       'Fine. Be that way!'
    when shouting(txt)
       'Woah, chill out!'
    when ambivalence(txt)
       'Whatever.'
    when numeric_ambivalence(txt)
       'Sure.'
    else
       '.'
    end
  end

  private

  def saying_nothing(txt)
    txt.delete(' ').empty?
  end

  def shouting(txt)
    false
  end

  def numeric_ambivalence(txt)
    false
  end

  def ambivalence(txt)
    false
  end
end
