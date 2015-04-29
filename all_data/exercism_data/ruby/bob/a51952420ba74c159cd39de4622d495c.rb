class Bob
  def hey(sentence)
    if empty?(sentence)
      'Fine. Be that way.'
    elsif question?(sentence)
      'Sure.'
    elsif upcase?(sentence)
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  private

  def question?(sentence)
    sentence.end_with?('?')
  end

  def upcase?(sentence)
    sentence == sentence.upcase
  end

  def empty?(sentence)
    sentence.nil? || sentence.empty?
  end
end
