class Bob

  def hey(phrase)
    if phrase !~ /[^[:space:]]/
      'Fine. Be that way!'
    elsif phrase == phrase.upcase
      'Woah, chill out!'
    elsif phrase.end_with?('?')
        'Sure.'
    else
      'Whatever.'
    end
  end

end
