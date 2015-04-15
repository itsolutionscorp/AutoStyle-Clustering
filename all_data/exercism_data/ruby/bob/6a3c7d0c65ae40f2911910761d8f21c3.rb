class Bob
  def hey(message)
    if silence?(message)
      'Fine. Be that way!'
    elsif yelling?(message)
      'Woah, chill out!'
    elsif question?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def silence?(text)
    text.strip.empty?
  end

  def yelling?(text)
    /[A-Z]/ =~ text && text == text.upcase
  end

  def question?(text)
    text.end_with?('?')
  end
end
