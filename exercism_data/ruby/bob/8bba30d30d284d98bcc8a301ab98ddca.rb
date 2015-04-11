class Bob
  def hey phrase
    if phrase.strip.empty?
      then 'Fine. Be that way!'
    elsif phrase.upcase == phrase && phrase=~/[A-Z]/
      then 'Woah, chill out!'
    elsif phrase.end_with?('?')
      then 'Sure.'
    else
      'Whatever.'
    end
  end
end
