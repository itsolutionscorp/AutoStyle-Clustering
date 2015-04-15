class Bob
  def hey(sentence)
    if yelling?(sentence)
      'Woah, chill out!'
    elsif question?(sentence)
      'Sure.'
    elsif nothing?(sentence)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private
  def yelling?(string)
    string == string.upcase unless nothing?(string)
  end

  def question?(string)
    !string.nil? && (string.end_with? '?')
  end

  def nothing?(string)
    string.nil? || string.strip.empty?
  end
end
