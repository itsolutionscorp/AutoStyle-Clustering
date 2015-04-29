class Bob
  def hey phrase
    if phrase == phrase.upcase && phrase != phrase.downcase
      'Woah, chill out!'
    elsif phrase[-1] == '?'
      'Sure.'
    elsif phrase.delete(' ').empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
