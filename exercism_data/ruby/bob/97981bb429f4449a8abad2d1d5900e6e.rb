class Bob
  def hey(text)
    if silence?(text)
      'Fine. Be that way!'
    elsif screaming?(text)
      'Woah, chill out!'
    elsif question?(text)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silence?(text)
    text.to_s.strip.empty?
  end

  def screaming?(text)
    text == text.upcase
  end

  def question?(text)
    text.end_with?('?')
  end
end
