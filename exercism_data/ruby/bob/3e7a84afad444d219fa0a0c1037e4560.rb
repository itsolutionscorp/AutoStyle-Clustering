class Bob

  def hey(phrase)
    if phrase.to_s.strip.empty?
      'Fine. Be that way!'
    elsif phrase == phrase.upcase    
      'Woah, chill out!'
    elsif phrase.match(/(.*)\?$/i)
      'Sure.'
    else
      'Whatever.'
    end
  end
end
