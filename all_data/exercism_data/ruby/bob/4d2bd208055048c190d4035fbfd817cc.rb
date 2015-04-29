class Bob
  def silence?(text)
    text.strip.empty?
  end

  def yelling?(text)
    text == text.upcase
  end

  def question?(text)
    text.ends_with? '?'
  end

  def hey(text)
    return 'Fine. Be that way!' if silence? text
    return 'Woah, chill out!'   if yelling? text
    return 'Sure.'              if question? text

    'Whatever.'
  end
end
