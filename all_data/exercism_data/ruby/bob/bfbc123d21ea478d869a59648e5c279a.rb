class Bob
  def hey(text)
    case 
    when all_caps?(text)
      'Woah, chill out!'
    when question?(text)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def has_lowercase?(text)
    return true if /[[:lower:]]/.match(text)
  end

  def all_caps?(text)
    !has_lowercase?(text)
  end

  def question?(text)
    return true if /\?$/.match(text)
  end

end
