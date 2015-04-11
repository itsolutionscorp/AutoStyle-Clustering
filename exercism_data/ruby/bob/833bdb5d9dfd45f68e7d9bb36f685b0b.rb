class Bob
  def hey(phrase_init)
    if phrase_init.strip.empty?
      'Fine. Be that way!'
    elsif phrase_init == phrase_init.upcase && (phrase_init.count "A-Z") > 0
      'Woah, chill out!'
    elsif phrase_init.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
