class Bob
  def hey(text)
    case 
    when silence?(text)
      'Fine. Be that way!'
    when all_caps?(text)
      'Woah, chill out!'
    when question?(text)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def silence?(text)
    text.strip.empty?
  end

  def has_lowercase?(text)
    /[[:lower:]]/.match(text) ? true : false
  end

  def has_upcase?(text)
    /[[:upper:]]/.match(text) ? true : false
  end

  def has_digits?(text)
    /[[:digit:]]/.match(text) ? true : false
  end

  def all_caps?(text)
    has_upcase?(text) && !has_lowercase?(text)
  end

  def question?(text)
    /\?\Z/.match(text) ? true : false
  end

end
