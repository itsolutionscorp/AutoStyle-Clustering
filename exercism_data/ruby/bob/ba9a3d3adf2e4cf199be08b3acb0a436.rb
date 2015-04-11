class Bob
  def hey(phrase)
    case
    when phrase.strip.empty?
      return 'Fine. Be that way!'
    when phrase =~ /[A-Z]/ && phrase.upcase == phrase
      return 'Woah, chill out!'
    when phrase.end_with?('?')
      return 'Sure.'
    else
      return 'Whatever.'
    end
  end
end
