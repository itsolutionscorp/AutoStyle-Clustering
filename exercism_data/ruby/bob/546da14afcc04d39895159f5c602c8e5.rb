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
  def yelling?(sentence)
    sentence == sentence.upcase unless nothing?(sentence)
  end

  def question?(sentence)
    sentence.end_with?('?') unless nothing?(sentence)
  end

  def nothing?(sentence)
    sentence.nil? || sentence.strip.empty?
  end
end
