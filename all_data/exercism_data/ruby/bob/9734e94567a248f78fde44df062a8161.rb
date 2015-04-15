class Bob
  def hey(sentence)
    return 'Fine. Be that way!' if empty?(sentence)
    return 'Woah, chill out!' if yell?(sentence)
    return 'Sure.' if question?(sentence)
    return 'Whatever.'
  end

  private

  def empty?(sentence)
    sentence.rstrip.length.eql? 0
  end

  def yell?(sentence)
    sentence =~ /[a-zA-Z]/ and sentence.upcase.eql? sentence
  end

  def question?(sentence)
    sentence.end_with? '?'
  end
end
