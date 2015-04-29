class Bob
  def hey(text)
    if is_yelling? text
      'Woah, chill out!'
    elsif is_asking? text
      'Sure.'
    elsif is_silence? text
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private
  def is_yelling?(text)
    (text.upcase == text) && (text.downcase != text)
  end

  def is_asking?(text)
    text.end_with? '?'
  end

  def is_silence?(text)
    text.strip.empty?
  end
end
