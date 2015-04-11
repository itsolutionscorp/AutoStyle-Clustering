class Bob
  def hey(sentence)
    return 'Fine. Be that way.' if nothing?(sentence)
    return 'Woah, chill out!'   if shouting?(sentence)
    return 'Sure.'              if question?(sentence)
    'Whatever.'
  end

  private

  def nothing?(sentence)
    sentence.nil? || sentence.empty?
  end

  def shouting?(sentence)
    sentence.upcase == sentence
  end

  def question?(sentence)
    sentence.end_with?('?')
  end
end
