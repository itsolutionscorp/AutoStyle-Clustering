class Bob
  def hey(phrase)
    @phrase = phrase
    return 'Fine. Be that way!' if nothing?
    return 'Woah, chill out!' if yell?
    return 'Sure.' if question?
    return 'Whatever.'
  end

  private
  def yell?
    @phrase.upcase == @phrase
  end

  def question?
    @phrase.end_with?("?")
  end

  def nothing?
    @phrase =~ /\A\s*\Z/
  end
end
