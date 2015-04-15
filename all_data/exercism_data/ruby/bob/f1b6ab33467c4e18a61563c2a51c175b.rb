class Bob
  def hey(phrase)
    phrase.to_s.strip!
    case
    when phrase.empty?
      'Fine. Be that way!'
    when phrase =~ /[A-Z]/ && phrase.upcase == phrase
      'Woah, chill out!'
    when phrase.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
