class Bob
  def hey(phrase)
    case
    when stared_at?(phrase)
      return 'Fine. Be that way!'
    when yelled_at?(phrase)
      return 'Woah, chill out!'
    when questioned?(phrase)
      return 'Sure.'
    else
      return 'Whatever.'
    end
  end

  private

  def stared_at?(phrase)
    return phrase.strip.empty?
  end

  def yelled_at?(phrase)
    return phrase =~ /[A-Z]/ && phrase.upcase == phrase
  end

  def questioned?(phrase)
    return phrase.end_with?('?')
  end
end
