class Bob
  def hey(parental_wisdom)
    return 'Fine. Be that way.' if silence?(parental_wisdom)
    return 'Woah, chill out!'   if shouting?(parental_wisdom)
    return 'Sure.'              if question?(parental_wisdom)

    'Whatever.'
  end

  private

  def silence?(parental_wisdom)
    parental_wisdom.nil? || parental_wisdom.empty?
  end

  def shouting?(parental_wisdom)
    parental_wisdom.upcase == parental_wisdom
  end

  def question?(parental_wisdom)
    parental_wisdom =~ /\?\Z/
  end
end
