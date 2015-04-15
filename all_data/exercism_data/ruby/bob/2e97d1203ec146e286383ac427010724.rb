class Bob
  def hey(string)
    case
    when silence?(string)
      'Fine. Be that way!'
    when all_caps?(string)
      'Woah, chill out!'
    when question?(string)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def all_caps?(string)
    ! string.match(/[a-z]/) && string.match(/[A-Z]/)
  end

  def question?(string)
    string.match(/\?\Z/)
  end

  def silence?(string)
    string.strip == ''
  end

end
