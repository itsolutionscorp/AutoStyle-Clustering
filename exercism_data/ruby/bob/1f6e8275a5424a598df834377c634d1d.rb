class Bob
  def hey(msg)
    if silence?(msg)
      return 'Fine. Be that way!'
    end
    if shouting?(msg)
      return 'Woah, chill out!'
    end
    if question?(msg)
      return 'Sure.'
    end
    'Whatever.'
  end

  private

  def silence?(msg)
    msg.strip == ''
  end

  def shouting?(msg)
    all_caps?(msg) && msg.match(/[[:alpha:]]/)
  end

  def question?(msg)
    msg.end_with?('?')
  end

  def all_caps?(str)
    str.upcase == str
  end
end
