class Bob

  def hey(sentence)
    return 'Fine. Be that way!' if silence?  sentence
    return 'Woah, chill out!'   if shouting? sentence
    return 'Sure.'              if question? sentence
    return 'Whatever.'
  end

  private

  def only_chars(sentence)
    sentence.gsub(/[^A-Za-z]/,'')
  end

  def shouting?(sentence)
    return false if only_chars(sentence).empty?
    only_chars(sentence).each_char.all? { |c| !!(c =~ /[A-Z]/) }
  end

  def question?(sentence)
    sentence[-1] == '?'
  end

  def silence?(sentence)
    sentence.strip.empty?
  end
  
end
