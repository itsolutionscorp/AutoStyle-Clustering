class Bob
  def hey(phrase)
    response = 'Sure.' if question?(phrase)
    response = 'Woah, chill out!' if yelling?(phrase)
    response = 'Fine. Be that way!' if silence?(phrase)
    response = 'Whatever.' if pattern_unknown?(response)
    response
  end

  private

  def question?(phrase)
    true if phrase.end_with?('?')
  end

  def yelling?(phrase)
    true if phrase.upcase == phrase && (/[a-zA-Z]/).match(phrase)
  end

  def silence?(phrase)
    true if phrase.split.length == 0
  end

  def pattern_unknown?(response)
    true if response == nil
  end
end
