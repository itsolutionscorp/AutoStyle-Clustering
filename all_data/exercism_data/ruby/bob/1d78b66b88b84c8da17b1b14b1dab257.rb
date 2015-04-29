class Bob
  def hey(phrase)
    if phrase.nil? || phrase.empty?
      'Fine. Be that way.'
    elsif phrase.upcase == phrase
      'Woah, chill out!'
    elsif phrase =~ /\?$/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
