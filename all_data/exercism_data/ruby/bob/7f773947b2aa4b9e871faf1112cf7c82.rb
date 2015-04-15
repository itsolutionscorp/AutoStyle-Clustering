class Bob

  def hey(phrase)
    if phrase == '' || phrase.match(/\s{2}/)
      'Fine. Be that way!'
    elsif phrase == phrase.upcase
      'Woah, chill out!'
    elsif phrase.split.last =~ /\?/
      'Sure.'
    else
      'Whatever.'
    end
  end

end
